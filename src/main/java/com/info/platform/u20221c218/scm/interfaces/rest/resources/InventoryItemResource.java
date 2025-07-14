package com.info.platform.u20221c218.scm.interfaces.rest.resources;

public record InventoryItemResource(
        Long Id,
        String productSku,
        String status,
        Double minimumQuantity,
        Double availableQuantity,
        Double reservedQuantity,
        Double pendingSupplyQuantity)
{
}
