package com.onesa.lms.LibraryManagementApi.domain.inventory.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.onesa.lms.LibraryManagementApi.core.utils.dtos.ApiResponse;
import com.onesa.lms.LibraryManagementApi.domain.inventory.models.Inventory;
import com.onesa.lms.LibraryManagementApi.domain.inventory.service.InventoryService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/inventory")
public class InventoryController {

    @Autowired
    private InventoryService inventoryService;

    @PostMapping
    public ResponseEntity<ApiResponse<Inventory>> addInventory(@RequestBody Inventory inventory) {

        try {
            Inventory createdInventory = inventoryService.createInventory(inventory);
            ApiResponse<Inventory> response = new ApiResponse<>(1200, "Inventory created Successfully",
                    createdInventory);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            ApiResponse<Inventory> response = new ApiResponse<>(1202, "Failed to create Inventory", null);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<Inventory>>> getAllInventory() {
        try {
            List<Inventory> inventories = inventoryService.getAllInventories();
            if (inventories.isEmpty()) {
                ApiResponse<List<Inventory>> response = new ApiResponse<>(1102, "No inventories found", inventories);
                return new ResponseEntity<>(response, HttpStatus.OK);
            }

            ApiResponse<List<Inventory>> response = new ApiResponse<>(1207, "Inventories retrieved successfully",
                    inventories);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            ApiResponse<List<Inventory>> response = new ApiResponse<>(1120, "Failed to retrieve inventories", null);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Inventory>> getInventory(@PathVariable Long id) {
        try {
            Inventory inventory = inventoryService.getInventoryById(id);
            if (inventory == null) {
                ApiResponse<Inventory> response = new ApiResponse<>(1105, "Inventory not found", null);
                return new ResponseEntity<>(response, HttpStatus.OK);
            }
            ApiResponse<Inventory> response = new ApiResponse<>(1104, "Inventory found", inventory);
            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (Exception e) {
            ApiResponse<Inventory> response = new ApiResponse<>(1120, "An error occurred", null);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Inventory>> updateInvetory(@PathVariable long id,
            @RequestBody Inventory updatedInventory) {
        try {
            Inventory inventory = inventoryService.updateInventory(id, updatedInventory);

            if (inventory == null) {
                ApiResponse<Inventory> response = new ApiResponse<>(1105, "Inventory not found", null);
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }

            ApiResponse<Inventory> response = new ApiResponse<>(1107, "Inventory updated successfully", inventory);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            ApiResponse<Inventory> response = new ApiResponse<>(1120, "Failed to update Inventory", null);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteInventory(@PathVariable long id) {
        try {
            boolean deleted = inventoryService.deleteInventory(id);
            if (!deleted) {
                ApiResponse<Void> response = new ApiResponse<>(1001, "Inventory not found", null);
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }

            ApiResponse<Void> response = new ApiResponse<>(1000, "Inventory deleted successfully", null);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            ApiResponse<Void> response = new ApiResponse<>(1120, "Failed to delete Inventory", null);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
