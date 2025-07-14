package com.info.platform.u20221c218.sd.domain.model.aggregates;

import com.info.platform.u20221c218.sd.domain.model.commands.CreateOrderItemCommand;
import com.info.platform.u20221c218.sd.domain.model.valueObjects.OrderItemStatus;
import com.info.platform.u20221c218.sd.domain.model.valueObjects.ProductId;
import com.info.platform.u20221c218.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.Date;
import java.util.UUID;

@Getter
@Entity
public class OrderItem extends AuditableAbstractAggregateRoot<OrderItem> {
    @Column(nullable = false)
    public Long orderId;
    @Embedded
    @Column(nullable = false)
    public ProductId productSku;
    @Column(nullable = false)
    public Double requestedQuantity;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    public OrderItemStatus status;
    @Column(nullable = false)
    public Date orderedAt;

    public OrderItem() {
        // Default constructor for JPA
    }

    public OrderItem(CreateOrderItemCommand command){
        if(command.orderId() == null || command.orderId() <= 0) {
            throw new IllegalArgumentException("Order ID cannot be null or negative");
        }
        if(command.requestedQuantity() == null || command.requestedQuantity() <= 0) {
            throw new IllegalArgumentException("Requested quantity cannot be null or negative");
        }
        if(command.productSku() == null || command.productSku().isBlank()) {
            throw new IllegalArgumentException("Product SKU cannot be null or blank");
        }
        if(!UUID.fromString(command.productSku()).toString().equals(command.productSku())) {
            throw new IllegalArgumentException("Invalid Product SKU format");
        }
        orderId= command.orderId();
        productSku = new ProductId(UUID.fromString(command.productSku()));
        requestedQuantity = command.requestedQuantity();
        status = OrderItemStatus.WAITING_FOR_INVENTORY; // Default status
        orderedAt = new Date();
    }




}
