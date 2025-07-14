package com.info.platform.u20221c218.sd.domain.services;

import com.info.platform.u20221c218.sd.domain.model.aggregates.OrderItem;
import com.info.platform.u20221c218.sd.domain.model.commands.CreateOrderItemCommand;

import java.util.Optional;

public interface OrderItemCommandService {
    Optional<OrderItem> Handle(CreateOrderItemCommand command);
}
