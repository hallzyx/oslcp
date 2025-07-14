package com.info.platform.u20221c218.sd.interfaces.rest.transform;

import com.info.platform.u20221c218.sd.domain.model.aggregates.OrderItem;
import com.info.platform.u20221c218.sd.interfaces.rest.resources.OrderItemResource;

public class OrderItemResourceFromEntityAssembler {
    public static OrderItemResource toResourceFromEntity(OrderItem entity) {
        return new OrderItemResource(
            entity.getId(),
            entity.getOrderId(),
            entity.getProductSku().productSku().toString(),
            entity.getRequestedQuantity(),
            entity.getStatus().name(),
            entity.getOrderedAt()
        );
    }
}
