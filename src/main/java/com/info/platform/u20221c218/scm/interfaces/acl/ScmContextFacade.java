package com.info.platform.u20221c218.scm.interfaces.acl;

import com.info.platform.u20221c218.scm.domain.model.aggregates.InventoryItem;
import com.info.platform.u20221c218.scm.domain.model.valueObjects.ProductId;

public interface ScmContextFacade {
    boolean existOrderInventoryByProductSku(String productSku);
    double calculateReserverQuantityInventoryItem(String productSku, Double requestedQuantity);
}
