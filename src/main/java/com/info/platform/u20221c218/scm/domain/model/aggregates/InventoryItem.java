package com.info.platform.u20221c218.scm.domain.model.aggregates;

import com.info.platform.u20221c218.scm.domain.model.commands.CreateInventoryItemCommand;
import com.info.platform.u20221c218.scm.domain.model.valueObjects.InventoryItemStatus;
import com.info.platform.u20221c218.scm.domain.model.valueObjects.ProductId;
import com.info.platform.u20221c218.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.UUID;
@Getter
@Entity
public class InventoryItem extends AuditableAbstractAggregateRoot<InventoryItem> {

    @Embedded
    @Column(nullable = false)
    public ProductId productSku;
    @Enumerated(EnumType.STRING)
    public InventoryItemStatus status;
    @Column(nullable = false)
    public double minimumQuantity;
    @Column(nullable = false)
    public double availableQuantity;
    @Column
    public double reservedQuantity;
    @Column
    public double pendingSupplyQuantity;

    public InventoryItem() {
        // Default constructor for JPA
    }
    public InventoryItem(CreateInventoryItemCommand command){

        if(command.availableQuantity() == null || command.availableQuantity() < 0) {
            throw new IllegalArgumentException("Available quantity cannot be null or negative");
        }
        if(command.minimumQuantity() == null || command.minimumQuantity() <= 0) {
            throw new IllegalArgumentException("Minimum quantity cannot be null or negative");
        }
        if(command.productSku() == null || command.productSku().isBlank()) {
            throw new IllegalArgumentException("Product SKU cannot be null or blank");
        }
        if(!UUID.fromString(command.productSku()).toString().equals(command.productSku())) {
            throw new IllegalArgumentException("Invalid Product SKU format");
        }

        if(command.availableQuantity()< 2*command.minimumQuantity()){
            throw new IllegalArgumentException("Available quantity must be at least twice the minimum quantity");
        }

        productSku= new ProductId(UUID.fromString(command.productSku()));
        minimumQuantity = command.minimumQuantity();
        availableQuantity = command.availableQuantity();
        reservedQuantity= 0;
        pendingSupplyQuantity = 0;
        status = InventoryItemStatus.WITH_STOCK;
    }

    public double setReservedQuantity(double requestedQuantity) {

        if (requestedQuantity > availableQuantity) {
            var difference = requestedQuantity - availableQuantity;
            reservedQuantity += availableQuantity;
            availableQuantity = 0;
            pendingSupplyQuantity += difference;
            status = InventoryItemStatus.WAITING_FOR_SUPPLY;
            return difference;
        }else{
            availableQuantity =  availableQuantity-requestedQuantity;
            reservedQuantity += requestedQuantity;
            if(availableQuantity < minimumQuantity){
                status = InventoryItemStatus.WAITING_FOR_SUPPLY;
            }
            return 0;
        }


    }
}
