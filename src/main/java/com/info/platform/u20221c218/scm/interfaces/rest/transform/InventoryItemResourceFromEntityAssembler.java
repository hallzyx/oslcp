package com.info.platform.u20221c218.scm.interfaces.rest.transform;

import com.info.platform.u20221c218.scm.domain.model.aggregates.InventoryItem;
import com.info.platform.u20221c218.scm.interfaces.rest.resources.InventoryItemResource;

public class InventoryItemResourceFromEntityAssembler {
    public static InventoryItemResource toResourceFromEntity(InventoryItem entity){
        return new InventoryItemResource(
            entity.getId(),
            entity.getProductSku().productSku().toString(),
            entity.getStatus().name(),
            entity.getMinimumQuantity(),
            entity.getAvailableQuantity(),
            entity.getReservedQuantity(),
            entity.getPendingSupplyQuantity()
        );
    }
}
