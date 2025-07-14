package com.info.platform.u20221c218.sd.application.internal.commands;

import com.info.platform.u20221c218.sd.application.outBoundServices.ExternalScmService;
import com.info.platform.u20221c218.sd.domain.model.aggregates.OrderItem;
import com.info.platform.u20221c218.sd.domain.model.commands.CreateOrderItemCommand;
import com.info.platform.u20221c218.sd.domain.model.valueObjects.OrderItemStatus;
import com.info.platform.u20221c218.sd.domain.model.valueObjects.ProductId;
import com.info.platform.u20221c218.sd.domain.services.OrderItemCommandService;
import com.info.platform.u20221c218.sd.infrastructure.persistence.jpa.repositories.OrderItemRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class OrderItemCommandServiceImp implements OrderItemCommandService {
    private final OrderItemRepository orderItemRepository;
    private final ExternalScmService externalScmService;
    public OrderItemCommandServiceImp(OrderItemRepository orderItemRepository, ExternalScmService externalScmService) {
        this.orderItemRepository = orderItemRepository;
        this.externalScmService = externalScmService;
    }
    @Override
    public Optional<OrderItem> Handle(CreateOrderItemCommand command) {

        var certainSku = new ProductId(UUID.fromString(command.productSku()));
        if(orderItemRepository.existsByOrderIdAndProductSku(command.orderId(),certainSku)){
            throw new IllegalArgumentException("Order item with SKU '" + command.productSku() + "' already exists in order " + command.orderId());
        }
        if(!externalScmService.existOrderInventoryByProductSku(command.productSku())){
            throw new IllegalArgumentException("Product with SKU '" + command.productSku() + "' does not exist in inventory.");
        }


        var newOrderItem = new OrderItem(command);
        if(externalScmService
                .calculateIfIsEnoughtAvailableQuantityWithReservedQuantity
                        (command.productSku(), command.requestedQuantity()) == 0){
                newOrderItem.status = OrderItemStatus.READY_FOR_DISPATCH;
        }else{
            newOrderItem.status= OrderItemStatus.WAITING_FOR_INVENTORY;
        }
        orderItemRepository.save(newOrderItem);
        return Optional.of(newOrderItem);

    }
}
