package org.arghya.app.inventoryservice.controller;

import lombok.RequiredArgsConstructor;
import org.arghya.app.inventoryservice.dto.InventoryRequest;
import org.arghya.app.inventoryservice.dto.InventoryResponse;
import org.arghya.app.inventoryservice.service.InventoryService;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/online-shopping/inventory")
@RequiredArgsConstructor
public class InventoryController {

    private final InventoryService inventoryService;

    @GetMapping("/stock")
    @ResponseStatus(HttpStatus.OK)
    public List<InventoryResponse> isInStock(@RequestParam("skuCodes")List<String> skuCodes) {
        return inventoryService.isInStock(skuCodes);
    }

    @PostMapping("/items")
    public HttpEntity<String> updateInventory(@RequestBody List<InventoryRequest> inventoryRequests) {
        inventoryService.updateInventory(inventoryRequests);
        return new ResponseEntity<>("inventory updated", HttpStatus.CREATED);
    }
}
