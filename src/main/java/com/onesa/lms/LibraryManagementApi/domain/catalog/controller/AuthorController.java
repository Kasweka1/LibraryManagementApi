package com.onesa.lms.LibraryManagementApi.domain.catalog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.onesa.lms.LibraryManagementApi.domain.catalog.dtos.ApiResponse;
import com.onesa.lms.LibraryManagementApi.domain.catalog.models.Author;
import com.onesa.lms.LibraryManagementApi.domain.catalog.service.AuthorService;

@RestController
@RequestMapping("/authors")
public class AuthorController {

    @Autowired
    private  AuthorService authorService;


    @PostMapping
    public ResponseEntity<ApiResponse<Author>> createAuthor(@RequestBody Author author){

        try {
            Author createdAuthor = authorService.createAuthor(author);
            ApiResponse<Author> response = new ApiResponse<>(1200, "Author created Successfully", createdAuthor);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
            
        } catch (Exception e) {
            ApiResponse<Author> response = new ApiResponse<>(1202, "Failed to create Author", null);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<Author>>> getAllAuthors(){
        
        try {
            List<Author> authors = authorService.getAllAuthors();
            
            if(authors.isEmpty()){
                ApiResponse<List<Author>> response = new ApiResponse<>(1102, "No authors found", authors);
                return new ResponseEntity<>(response, HttpStatus.OK);
            }

            ApiResponse<List<Author>> response = new ApiResponse<>(1207, "Authors retrieved successfully", authors);
            return new ResponseEntity<>(response, HttpStatus.OK);
            
        } catch (Exception e) {
            ApiResponse<List<Author>> response = new ApiResponse<>(1120, "Failed to retrieve authors", null);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Author>> getAuthor(@PathVariable long id) {

        try {
            Author author = authorService.getAuthorById(id);
            if (author == null) {
                ApiResponse<Author> response = new ApiResponse<>(1105, "Author not found", null);
                return new ResponseEntity<>(response, HttpStatus.OK);
            }

            ApiResponse<Author> response = new ApiResponse<>(1104, "Author found", author);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            ApiResponse<Author> response = new ApiResponse<>(1120, "An error occurred", null);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Author>> updateAuthor(@PathVariable long id,
            @RequestBody Author updatedAuthor) {
        try {
            Author author = authorService.updateAuthor(id, updatedAuthor);

            if (author == null) {
                ApiResponse<Author> response = new ApiResponse<>(1105, "Author not found", null);
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }

            ApiResponse<Author> response = new ApiResponse<>(1107, "Author updated successfully", author);
            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (Exception e) {
            ApiResponse<Author> response = new ApiResponse<>(1120, "Failed to update Author", null);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteAuthor(@PathVariable long id) {
        try {
            boolean deleted = authorService.deleteAuthor(id);

            if (!deleted) {
                ApiResponse<Void> response = new ApiResponse<>(1001, "Author not found", null);
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }

            ApiResponse<Void> response = new ApiResponse<>(1000, "Author deleted successfully", null);
            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (Exception e) {
            ApiResponse<Void> response = new ApiResponse<>(1120, "Failed to delete Author", null);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
