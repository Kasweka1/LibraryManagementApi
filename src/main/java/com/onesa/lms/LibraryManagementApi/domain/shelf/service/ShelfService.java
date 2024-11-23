package com.onesa.lms.LibraryManagementApi.domain.shelf.service;

import java.util.List;

import com.onesa.lms.LibraryManagementApi.domain.shelf.model.Shelf;

public interface ShelfService {
    
    // Add Shelf
    Shelf createShelf(Shelf shelf);

    // List of Shelves
    List<Shelf> getAllShelves();

    // Get shlef by id
    Shelf getShelfById(long id);

    // Update shelf

    Shelf updateShelf(Shelf shelf, long id);

    // Delete Shelf

    boolean deleteShelf(long id);

    
}
