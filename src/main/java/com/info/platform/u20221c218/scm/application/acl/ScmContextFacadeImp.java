package com.info.platform.u20221c218.scm.application.acl;

import com.info.platform.u20221c218.scm.domain.model.aggregates.InventoryItem;
import com.info.platform.u20221c218.scm.domain.model.valueObjects.ProductId;
import com.info.platform.u20221c218.scm.infrastructure.persistence.jpa.repositories.InventoryItemRepository;
import com.info.platform.u20221c218.scm.interfaces.acl.ScmContextFacade;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ScmContextFacadeImp implements ScmContextFacade {
    private final InventoryItemRepository inventoryItemRepository;
    public ScmContextFacadeImp(InventoryItemRepository inventoryItemRepository) {
        this.inventoryItemRepository = inventoryItemRepository;
    }
    @Override
    public boolean existOrderInventoryByProductSku(String productSku) {

        return inventoryItemRepository.existsByProductSku(new ProductId(UUID.fromString(productSku)));
    }

    @Override
    public double calculateReserverQuantityInventoryItem(String productSku, Double requestedQuantity) {
        var certainInventoryItem = inventoryItemRepository.findFirstByProductSku(new ProductId(UUID.fromString(productSku)));
        return certainInventoryItem.setReservedQuantity(requestedQuantity);
    }
}
