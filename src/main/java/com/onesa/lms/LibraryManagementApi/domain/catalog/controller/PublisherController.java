package com.onesa.lms.LibraryManagementApi.domain.catalog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.onesa.lms.LibraryManagementApi.core.utils.dtos.ApiResponse;
import com.onesa.lms.LibraryManagementApi.domain.catalog.models.Publisher;
import com.onesa.lms.LibraryManagementApi.domain.catalog.service.PublisherService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


@RestController
@RequestMapping("/publishers")
public class PublisherController {
    
    @Autowired
    private PublisherService publisherService;

    @PostMapping
    public ResponseEntity<ApiResponse<Publisher>> createPublisher(@RequestBody Publisher publibser){
        try {
            Publisher createdPublisher = publisherService.createPublisher(publibser);
            ApiResponse<Publisher> response  = new ApiResponse<>(1200, "Publisher created Successfully", createdPublisher);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            ApiResponse<Publisher> response = new ApiResponse<>(1202, "Failed to create Publisher", null);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<Publisher>>> getAllPublisher(){
        try {
            List<Publisher> publishers = publisherService.getAllPublisher();

            if(publishers.isEmpty()){
                ApiResponse<List<Publisher>> response = new ApiResponse<>(1102, "No publishers found", publishers);
                return new ResponseEntity<>(response, HttpStatus.OK);
            }

            ApiResponse<List<Publisher>> response = new ApiResponse<>(1207, "Authors retrieved successfully", publishers);
            return new ResponseEntity<>(response, HttpStatus.OK);
            
        } catch (Exception e) {
            ApiResponse<List<Publisher>> response = new ApiResponse<>(1120, "Failed to retrieve authors", null);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Publisher>> getPublisher(@PathVariable long id) {

        try {
            Publisher Publisher = publisherService.getPublisherById(id);
            if (Publisher == null) {
                ApiResponse<Publisher> response = new ApiResponse<>(1105, "Publisher not found", null);
                return new ResponseEntity<>(response, HttpStatus.OK);
            }

            ApiResponse<Publisher> response = new ApiResponse<>(1104, "Publisher found", Publisher);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            ApiResponse<Publisher> response = new ApiResponse<>(1120, "An error occurred", null);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Publisher>> updatePublisher(@PathVariable long id,
            @RequestBody Publisher updatedPublisher) {
        try {
            Publisher publisher = publisherService.updatePublisher(id, updatedPublisher);

            if (publisher == null) {
                ApiResponse<Publisher> response = new ApiResponse<>(1105, "Publisher not found", null);
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }

            ApiResponse<Publisher> response = new ApiResponse<>(1107, "Publisher updated successfully", publisher);
            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (Exception e) {
            ApiResponse<Publisher> response = new ApiResponse<>(1120, "Failed to update Publisher", null);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deletePublisher(@PathVariable long id) {
        try {
            boolean deleted = publisherService.deletePublisher(id);

            if (!deleted) {
                ApiResponse<Void> response = new ApiResponse<>(1001, "Publisher not found", null);
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }

            ApiResponse<Void> response = new ApiResponse<>(1000, "Publisher deleted successfully", null);
            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (Exception e) {
            ApiResponse<Void> response = new ApiResponse<>(1120, "Failed to delete Publisher", null);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
