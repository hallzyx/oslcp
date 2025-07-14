package com.info.platform.u20221c218.scm.domain.model.commands;

public record CreateInventoryItemCommand(String productSku, Double minimumQuantity, Double availableQuantity)
{ }
