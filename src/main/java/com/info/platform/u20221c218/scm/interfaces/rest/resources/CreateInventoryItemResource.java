package com.info.platform.u20221c218.scm.interfaces.rest.resources;

public record CreateInventoryItemResource
        (String productSku, Double minimumQuantity, Double availableQuantity) {
}
