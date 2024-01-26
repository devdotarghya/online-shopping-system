package org.arghya.app.inventoryservice.repository;

import org.arghya.app.inventoryservice.model.Inventory;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InventoryRepository extends CrudRepository<Inventory, Long> {
    List<Inventory> findBySkuCodeIn(List<String> skuCodes);
}
