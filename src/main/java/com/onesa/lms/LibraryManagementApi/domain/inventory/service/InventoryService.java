package com.onesa.lms.LibraryManagementApi.domain.inventory.service;

import java.util.List;

import com.onesa.lms.LibraryManagementApi.domain.inventory.models.Inventory;

public interface InventoryService {
    
    // Add Inventry
    Inventory createInventory(Inventory inventory);

    // List of all Inventories
    List<Inventory> getAllInventories();

    // Get Inventory by Id
    Inventory getInventoryById(long id);

    // Update Inventory
    Inventory updateInventory(long id, Inventory inventory);

    // Delete Inventory
    boolean deleteInventory(long id);
    
    
}
