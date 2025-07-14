package com.info.platform.u20221c218.sd.interfaces.rest.transform;

import com.info.platform.u20221c218.sd.domain.model.commands.CreateOrderItemCommand;
import com.info.platform.u20221c218.sd.interfaces.rest.resources.CreateOrderItemResource;

public class CreateOrderItemCommandFromResourceAssembler {
    public static CreateOrderItemCommand toCommandFromResource(
            Long orderId,
            CreateOrderItemResource resource
    ){
        return new CreateOrderItemCommand(
            orderId,
            resource.productSku(),
            resource.requestedQuantity()
        );
    }
}
