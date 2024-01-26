package org.arghya.app.inventoryservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.arghya.app.inventoryservice.dto.InventoryRequest;
import org.arghya.app.inventoryservice.dto.InventoryResponse;
import org.arghya.app.inventoryservice.model.Inventory;
import org.arghya.app.inventoryservice.repository.InventoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class InventoryService {

    private final InventoryRepository inventoryRepository;

    @Transactional
    public void updateInventory(List<InventoryRequest> inventoryRequests) {
        List<Inventory> inventories = inventoryRequests.stream()
                .map(this::mapToInventory)
                .collect(Collectors.toList());
        inventoryRepository.saveAll(inventories);
    }

    @Transactional(readOnly = true)
    public List<InventoryResponse> isInStock( List<String> skuCodes) {
        /*log.info("wait started");
        try{
            Thread.sleep(10000);
        } catch (Exception e){
            log.info(e.getMessage());
        }

        log.info("wait ended");*/
        return inventoryRepository.findBySkuCodeIn(skuCodes).stream()
                .map(inventory ->
                    InventoryResponse.builder()
                            .skuCode(inventory.getSkuCode())
                            .isInStock(inventory.getQuantity() > 0)
                            .build()
                ).collect(Collectors.toList());
    }

    private Inventory mapToInventory(InventoryRequest inventoryRequest) {
        Inventory inventory = new Inventory();
        inventory.setSkuCode(inventoryRequest.getSkuCode());
        inventory.setQuantity(inventoryRequest.getQuantity());
        return inventory;
    }
}
