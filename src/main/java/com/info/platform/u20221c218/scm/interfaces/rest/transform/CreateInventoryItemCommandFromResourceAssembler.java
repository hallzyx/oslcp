package com.info.platform.u20221c218.scm.interfaces.rest.transform;

import com.info.platform.u20221c218.scm.domain.model.commands.CreateInventoryItemCommand;
import com.info.platform.u20221c218.scm.interfaces.rest.resources.CreateInventoryItemResource;

public class CreateInventoryItemCommandFromResourceAssembler {
    public static CreateInventoryItemCommand toCommandFromResource(CreateInventoryItemResource resource) {
        return new CreateInventoryItemCommand(
            resource.productSku(),
            resource.minimumQuantity(),
            resource.availableQuantity()
        );
    }
}
