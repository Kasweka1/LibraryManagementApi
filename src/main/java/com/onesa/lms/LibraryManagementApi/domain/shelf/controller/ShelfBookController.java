package com.onesa.lms.LibraryManagementApi.domain.shelf.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.onesa.lms.LibraryManagementApi.domain.catalog.dtos.ApiResponse;
import com.onesa.lms.LibraryManagementApi.domain.inventory.controller.GetMapping;
import com.onesa.lms.LibraryManagementApi.domain.shelf.model.ShelfBook;
import com.onesa.lms.LibraryManagementApi.domain.shelf.service.ShelfBookService;

@RestController
@RequestMapping("/shelfbook")
public class ShelfBookController {
    
    @Autowired
    private ShelfBookService shelfBookService;
    
    @PostMapping
    public ResponseEntity<ApiResponse<ShelfBook>> createShelfBook(@RequestBody ShelfBook shelfBook){
        try {
            ShelfBook createdShelfBook = shelfBookService.createShelfBook(shelfBook);
            ApiResponse<ShelfBook> response = new ApiResponse<>(1200, "ShelfBook created Succesfully", createdShelfBook);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            ApiResponse<ShelfBook> response = new ApiResponse<>(1202, "Failed to create ShelfBook", null);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }

    @GetMapping("/")
    public ResponseEntity<ApiResponse<List<ShelfBook>>> getAllShelfBooks(){
        try {
            List<ShelfBook> shelfBooks = shelfBookService.getAllShelfBooks();
            if(shelfBooks.isEmpty()){
                ApiResponse<List<ShelfBook>> response = new ApiResponse<>(1102, "No ShelfBooks available", null);
                return new ResponseEntity<>(response, HttpStatus.OK);
            }

            ApiResponse<List<ShelfBook>> response = new ApiResponse<>(1207, "ShelfBooks retrieved successfully", shelfBooks);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            ApiResponse<List<ShelfBook>> response = new ApiResponse<>(1120, "Failed to retrieve shelfBooks", null);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ShelfBook>> getShelfBook(@PathVariable long id) {
        try {
            ShelfBook shelfBook = shelfBookService.getShelfBookById(id);
            if (shelfBook == null) {
                ApiResponse<ShelfBook> response = new ApiResponse<>(1102, "ShelfBook not found", null);
                return new ResponseEntity<>(response, HttpStatus.OK);
            }
            ApiResponse<ShelfBook> response = new ApiResponse<>(1207, "ShelfBook retrieved successfully", shelfBook);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            ApiResponse<ShelfBook> response = new ApiResponse<>(1120, "Failed to retrieve ShelfBook", null);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<ShelfBook>> updateShelfBook(@PathVariable long id, @RequestBody ShelfBook shelfBook) {
        try {
            ShelfBook updatedShelfBook = shelfBookService.updateShelfBook(id, shelfBook.getBook().getId(), shelfBook.getShelf().getId());
            ApiResponse<ShelfBook> response = new ApiResponse<>(1207, "ShelfBook updated successfully", updatedShelfBook);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            ApiResponse<ShelfBook> response = new ApiResponse<>(1120, "Failed to update ShelfBook", null);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<ShelfBook>> deleteShelfBook(@PathVariable long id) {
        try {
            boolean isDeleted = shelfBookService.deleteShelfBook(id);
            if (!isDeleted) {
                ApiResponse<ShelfBook> response = new ApiResponse<>(1102, "ShelfBook not found", null);
                return new ResponseEntity<>(response, HttpStatus.OK);
            }
            ApiResponse<ShelfBook> response = new ApiResponse<>(1207, "ShelfBook deleted successfully", null);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            ApiResponse<ShelfBook> response = new ApiResponse<>(1120, "Failed to delete ShelfBook", null);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }

}
