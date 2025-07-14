package com.info.platform.u20221c218.scm.domain.services;

import com.info.platform.u20221c218.scm.domain.model.aggregates.InventoryItem;
import com.info.platform.u20221c218.scm.domain.model.commands.CreateInventoryItemCommand;

import java.util.Optional;

public interface InventoryItemCommandService {
    Optional<InventoryItem> Handle(CreateInventoryItemCommand command);
}
