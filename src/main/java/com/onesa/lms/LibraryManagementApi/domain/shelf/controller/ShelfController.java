package com.onesa.lms.LibraryManagementApi.domain.shelf.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.onesa.lms.LibraryManagementApi.domain.catalog.dtos.ApiResponse;
import com.onesa.lms.LibraryManagementApi.domain.catalog.models.Author;
import com.onesa.lms.LibraryManagementApi.domain.shelf.model.Shelf;
import com.onesa.lms.LibraryManagementApi.domain.shelf.service.ShelfService;

@RestController
@RequestMapping("/shelf")
public class ShelfController {
    @Autowired 
    private ShelfService shelfService;

    @PostMapping
    public ResponseEntity<ApiResponse<Shelf>> createShelf(@RequestBody Shelf shelf ){
        try {
            Shelf createdShelf = shelfService.createShelf(shelf);
            ApiResponse<Shelf> response = new ApiResponse<>(1200, "Shelf created Succesfully", createdShelf);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
          ApiResponse<Shelf> response = new ApiResponse<>(1202, "Failed to create Shelf", null);
          return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }


    @GetMapping
    public ResponseEntity<ApiResponse<List<Shelf>>> getAllShelves(){
        try {
           List<Shelf> shelves = shelfService.getAllShelves();
           if(shelves.isEmpty()){
            ApiResponse<List<Shelf>> response = new ApiResponse<>(1102, "No Shelves available", null);
            return new ResponseEntity<>(response, HttpStatus.OK);
           } 

           ApiResponse<List<Shelf>> response = new ApiResponse<>(1207, "Shleves  retrieved successfully", shelves);
           return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            ApiResponse<List<Shelf>> response = new ApiResponse<>(1120, "Failed to retrieve shelves", null);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Shelf>> getAuthor(@PathVariable long id) {

        try {
            Shelf shelf = shelfService.getShelfById(id);
            if (shelf == null) {
                ApiResponse<Shelf> response = new ApiResponse<>(1105, "Shelf not found", null);
                return new ResponseEntity<>(response, HttpStatus.OK);
            }

            ApiResponse<Shelf> response = new ApiResponse<>(1104, "Shelf found", shelf);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            ApiResponse<Shelf> response = new ApiResponse<>(1120, "An error occurred", null);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // @PutMapping("/{id}")
    // public ResponseEntity<ApiResponse<Shelf>> updateAuthor(@PathVariable long id,
    //         @RequestBody Shelf updatedShelf) {
    //     try {
    //         Shelf shelf = shelfService.updateShelf(id, updatedShelf);

    //         if (shelf == null) {
    //             ApiResponse<Shelf> response = new ApiResponse<>(1105, "Shelf not found", null);
    //             return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    //         }

    //         ApiResponse<Shelf> response = new ApiResponse<>(1107, "Author updated successfully", shelf);
    //         return new ResponseEntity<>(response, HttpStatus.OK);

    //     } catch (Exception e) {
    //         ApiResponse<Author> response = new ApiResponse<>(1120, "Failed to update Author", null);
    //         return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    //     }
    // }

    // @DeleteMapping("/{id}")
    // public ResponseEntity<ApiResponse<Void>> deleteAuthor(@PathVariable long id) {
    //     try {
    //         boolean deleted = authorService.deleteAuthor(id);

    //         if (!deleted) {
    //             ApiResponse<Void> response = new ApiResponse<>(1001, "Author not found", null);
    //             return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    //         }

    //         ApiResponse<Void> response = new ApiResponse<>(1000, "Author deleted successfully", null);
    //         return new ResponseEntity<>(response, HttpStatus.OK);

    //     } catch (Exception e) {
    //         ApiResponse<Void> response = new ApiResponse<>(1120, "Failed to delete Author", null);
    //         return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    //     }
    // }
    
}
