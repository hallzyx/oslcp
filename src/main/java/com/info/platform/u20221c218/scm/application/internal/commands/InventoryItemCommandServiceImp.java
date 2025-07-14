package com.info.platform.u20221c218.scm.application.internal.commands;

import com.info.platform.u20221c218.scm.domain.model.aggregates.InventoryItem;
import com.info.platform.u20221c218.scm.domain.model.commands.CreateInventoryItemCommand;
import com.info.platform.u20221c218.scm.domain.model.valueObjects.ProductId;
import com.info.platform.u20221c218.scm.domain.services.InventoryItemCommandService;
import com.info.platform.u20221c218.scm.infrastructure.persistence.jpa.repositories.InventoryItemRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;
@Service
public class InventoryItemCommandServiceImp implements InventoryItemCommandService {

    private final InventoryItemRepository inventoryItemRepository;
    public InventoryItemCommandServiceImp(InventoryItemRepository inventoryItemRepository) {
        this.inventoryItemRepository = inventoryItemRepository;
    }
    @Override
    public Optional<InventoryItem> Handle(CreateInventoryItemCommand command) {

        var certainSku =new ProductId(UUID.fromString(command.productSku()));
        if(inventoryItemRepository.existsByProductSku(certainSku)) {
            throw new IllegalArgumentException("Inventory item with product SKU '" + command.productSku() + "' already exists.");
        }

        var inventoryItem = new InventoryItem(command);
        inventoryItemRepository.save(inventoryItem);
        return Optional.of(inventoryItem);

    }
}
