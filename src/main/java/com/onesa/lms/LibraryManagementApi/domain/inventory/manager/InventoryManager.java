package com.onesa.lms.LibraryManagementApi.domain.inventory.manager;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onesa.lms.LibraryManagementApi.domain.inventory.models.Inventory;
import com.onesa.lms.LibraryManagementApi.domain.inventory.repository.InventoryRepository;
import com.onesa.lms.LibraryManagementApi.domain.inventory.service.InventoryService;

@Service
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
      return inventoryRepository.findInventoryById(id);
    }

    @Override
    public Inventory updateInventory(long id, Inventory inventory) {
      Inventory existingInventory = inventoryRepository.findInventoryById(id);
      existingInventory.setName(inventory.getName());
      existingInventory.setDescription(inventory.getDescription());
      existingInventory.setQuantity(inventory.getQuantity());
      return inventoryRepository.save(existingInventory);
    }

    @Override
    public boolean deleteInventory(long id) {
       inventoryRepository.deleteById(id);
       return false;
    }
    
}
