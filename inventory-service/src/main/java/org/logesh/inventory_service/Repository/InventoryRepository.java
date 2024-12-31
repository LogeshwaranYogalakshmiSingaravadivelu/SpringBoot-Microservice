package org.logesh.inventory_service.Repository;

import org.logesh.inventory_service.Model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {

    List<Object> findBySkuCodeIn(List<String> skuCode);
}
