package org.arghya.app.inventoryservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InventoryRequest {
    private String skuCode;
    private Integer quantity;
}
