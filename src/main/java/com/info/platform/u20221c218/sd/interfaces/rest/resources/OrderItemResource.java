package com.info.platform.u20221c218.sd.interfaces.rest.resources;

import java.util.Date;

public record OrderItemResource(
        Long id,
        Long orderId,
        String productSku,
        Double requestedQuantity,
        String status,
        Date orderedAt
) {
}
