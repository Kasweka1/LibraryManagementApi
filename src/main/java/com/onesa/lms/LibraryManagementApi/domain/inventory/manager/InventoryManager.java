package com.onesa.lms.LibraryManagementApi.domain.inventory.manager;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.onesa.lms.LibraryManagementApi.domain.inventory.models.Inventory;
import com.onesa.lms.LibraryManagementApi.domain.inventory.repository.InventoryRepository;
import com.onesa.lms.LibraryManagementApi.domain.inventory.service.InventoryService;

public class InventoryManager implements InventoryService {

    @Autowired
    private InventoryRepository inventoryRepository;

    @Override
    public Inventory createInventory(Inventory inventory) {
       return inventoryRepository.save(inventory);
    }

    @Override
    public List<Inventory> getAllInventories() {
       return inventoryRepository.findAll();
    }

    @Override
    public Inventory getInventoryById(long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getInventoryById'");
    }

    @Override
    public Inventory updateInventory(long id, Inventory inventory) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateInventory'");
    }

    @Override
    public boolean deleteInventory(long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteInventory'");
    }
    
}
