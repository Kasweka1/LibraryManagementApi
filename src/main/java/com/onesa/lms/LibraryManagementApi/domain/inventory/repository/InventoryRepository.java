package com.onesa.lms.LibraryManagementApi.domain.inventory.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.onesa.lms.LibraryManagementApi.domain.inventory.models.Inventory;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Long>{
    
    Inventory findByName(String name);
    
    Inventory findInventoryById(long id);
}
