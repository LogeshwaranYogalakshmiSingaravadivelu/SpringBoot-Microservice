package org.logesh.inventory_service.Service;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.logesh.inventory_service.Dto.InventoryResponse;
import org.logesh.inventory_service.Repository.InventoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.logesh.inventory_service.Model.Inventory;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@Slf4j
public class InventoryService {

    private final InventoryRepository inventoryRepository;

    public InventoryService(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    @Transactional(readOnly = true)
    @SneakyThrows
    public List<InventoryResponse> isInStock(List<String> skuCode){
        log.info("Wait Started");
//        Thread.sleep(10000);
        log.info("Wait Ended");
        return inventoryRepository.findBySkuCodeIn(skuCode).stream()
                .map(inventory -> {
                    Inventory inv = (Inventory) inventory;
                    return InventoryResponse.builder()
                            .skuCode(inv.getSkuCode())
                            .isInStock(inv.getQuantity() > 0)
                            .build();
                }).toList();
    }
}
