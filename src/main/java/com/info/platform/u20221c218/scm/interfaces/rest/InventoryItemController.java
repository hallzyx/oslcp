package com.info.platform.u20221c218.scm.interfaces.rest;

import com.info.platform.u20221c218.scm.domain.services.InventoryItemCommandService;
import com.info.platform.u20221c218.scm.interfaces.rest.resources.CreateInventoryItemResource;
import com.info.platform.u20221c218.scm.interfaces.rest.resources.InventoryItemResource;
import com.info.platform.u20221c218.scm.interfaces.rest.transform.CreateInventoryItemCommandFromResourceAssembler;
import com.info.platform.u20221c218.scm.interfaces.rest.transform.InventoryItemResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api/inventory-items", produces = APPLICATION_JSON_VALUE)
@Tag(name= "Inventory Items", description = "Endpoints for managing inventory items")
public class InventoryItemController {
    private final InventoryItemCommandService inventoryItemCommandService;
    public InventoryItemController(InventoryItemCommandService inventoryItemCommandService) {
        this.inventoryItemCommandService = inventoryItemCommandService;
    }

    @PostMapping
    @Operation(summary = "Create a new Inventory Item")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Inventory Item created successfully",
            content = @Content(
                            mediaType = APPLICATION_JSON_VALUE,
                            schema =@Schema(implementation = InventoryItemResource.class)
                    )),
            @ApiResponse(responseCode = "400", description = "Invalid input data"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<?> CreateInventoryItem(@RequestBody CreateInventoryItemResource resource) {
        try{
            var command = CreateInventoryItemCommandFromResourceAssembler.toCommandFromResource(resource);
            var newInventoryItem = inventoryItemCommandService.Handle(command).map(
                    InventoryItemResourceFromEntityAssembler::toResourceFromEntity
            ).orElseThrow(
                    () -> new RuntimeException("Failed to create Inventory Item")
            );
            return ResponseEntity.status(HttpStatus.CREATED).body(newInventoryItem);
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
