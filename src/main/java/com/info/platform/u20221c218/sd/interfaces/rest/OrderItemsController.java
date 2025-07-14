package com.info.platform.u20221c218.sd.interfaces.rest;
import com.info.platform.u20221c218.sd.domain.services.OrderItemCommandService;
import com.info.platform.u20221c218.sd.interfaces.rest.resources.CreateOrderItemResource;
import com.info.platform.u20221c218.sd.interfaces.rest.transform.CreateOrderItemCommandFromResourceAssembler;
import com.info.platform.u20221c218.sd.interfaces.rest.transform.OrderItemResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api/v1/orders", produces = APPLICATION_JSON_VALUE)
@Tag(name= "Order Items", description = "Available Order Items Endpoints")
public class OrderItemsController {
    private final OrderItemCommandService orderItemCommandService;
    public OrderItemsController(OrderItemCommandService orderItemCommandService) {
        this.orderItemCommandService = orderItemCommandService;
    }

    @PostMapping("/{orderId}/items")
    public ResponseEntity<?> CreateOrderItem(
            @PathVariable Long orderId,
            @RequestBody CreateOrderItemResource resource
            ){
        try{
            var command = CreateOrderItemCommandFromResourceAssembler
                    .toCommandFromResource(orderId, resource);

            var newOrderItem = orderItemCommandService.Handle(command).map(
                    OrderItemResourceFromEntityAssembler::toResourceFromEntity
            ).orElseThrow(
                    () -> new RuntimeException("Failed to create Order Item")
            );
            return ResponseEntity.status(HttpStatus.CREATED).body(newOrderItem);

        }catch (Exception e){
            return ResponseEntity.badRequest().body(
                    Map.of(
                            "error", e.getMessage(),
                            "status", HttpStatus.BAD_REQUEST
                    )
            );
        }

    }
}
