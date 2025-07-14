package com.info.platform.u20221c218.scm.infrastructure.persistence.jpa.repositories;

import com.info.platform.u20221c218.scm.domain.model.aggregates.InventoryItem;
import com.info.platform.u20221c218.scm.domain.model.valueObjects.ProductId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InventoryItemRepository extends JpaRepository<InventoryItem, Long> {

    boolean existsByProductSku(ProductId productSku);
    InventoryItem findFirstByProductSku(ProductId productSku);
}
