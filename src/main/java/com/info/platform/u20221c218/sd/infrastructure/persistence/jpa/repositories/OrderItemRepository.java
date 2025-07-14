package com.info.platform.u20221c218.sd.infrastructure.persistence.jpa.repositories;

import com.info.platform.u20221c218.sd.domain.model.aggregates.OrderItem;
import com.info.platform.u20221c218.sd.domain.model.valueObjects.ProductId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

    boolean existsByOrderIdAndProductSku(Long orderId, ProductId productSku);
}
