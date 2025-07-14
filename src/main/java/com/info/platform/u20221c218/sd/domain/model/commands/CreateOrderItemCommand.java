package com.info.platform.u20221c218.sd.domain.model.commands;

public record CreateOrderItemCommand(
        Long orderId,
        String productSku,
        Double requestedQuantity
        ) {
}
