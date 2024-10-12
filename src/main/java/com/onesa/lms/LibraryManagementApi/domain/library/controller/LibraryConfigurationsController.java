package com.onesa.lms.LibraryManagementApi.domain.library.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.onesa.lms.LibraryManagementApi.domain.catalog.dtos.ApiResponse;
import com.onesa.lms.LibraryManagementApi.domain.library.models.LibraryConfigurations;
import com.onesa.lms.LibraryManagementApi.domain.library.service.LibraryConfigurationsService;

@RestController
@RequestMapping("/library-configs")
public class LibraryConfigurationsController {

    @Autowired
    public LibraryConfigurationsService libraryConfigurationsService;

    @GetMapping
    public ResponseEntity<ApiResponse<LibraryConfigurations>> getLibraryConfiguration() {
        try {
            LibraryConfigurations config = libraryConfigurationsService.getLibraryConfigurations();
            

            if (config == null) {
                ApiResponse<LibraryConfigurations> response = new ApiResponse<>(1404, "Library configuration not found",
                        null);
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }

            ApiResponse<LibraryConfigurations> response = new ApiResponse<>(1200,
                    "Library configuration retrieved successfully", config);
            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (Exception e) {
            ApiResponse<LibraryConfigurations> response = new ApiResponse<>(1500,
                    "Failed to retrieve library configuration", null);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // PUT: Update the existing library configuration
    @PutMapping
    public ResponseEntity<ApiResponse<LibraryConfigurations>> updateLibraryConfiguration(
            @RequestBody LibraryConfigurations libraryConfigurations) {
        try {
            // Only one configuration exists, so just update the current one
            LibraryConfigurations updatedConfig = libraryConfigurationsService
                    .updateLibraryConfigurations(libraryConfigurations);

            ApiResponse<LibraryConfigurations> response = new ApiResponse<>(1201,
                    "Library configuration updated successfully", updatedConfig);
            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (Exception e) {
            ApiResponse<LibraryConfigurations> response = new ApiResponse<>(1500,
                    "Failed to update library configuration", null);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
