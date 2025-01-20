package com.onesa.lms.LibraryManagementApi.domain.library.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.onesa.lms.LibraryManagementApi.core.utils.dtos.ApiResponse;
import com.onesa.lms.LibraryManagementApi.domain.library.models.LibraryCharges;
import com.onesa.lms.LibraryManagementApi.domain.library.service.LibraryChargesService;

@RestController
@RequestMapping("/library-charges")
public class LibraryChargesController {

    @Autowired
    public LibraryChargesService libraryChargesService;

    @GetMapping
    public ResponseEntity<ApiResponse<LibraryCharges>>getLibraryCharges(){
        try {
            LibraryCharges charges = libraryChargesService.getLibraryCharges();

            if(charges == null){
                ApiResponse<LibraryCharges> response = new ApiResponse<>(1404, "Library charges not found",
                null);

                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }

            ApiResponse<LibraryCharges> response = new ApiResponse<>(1200,
            "Library Charges retrieved successfully", charges);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            ApiResponse<LibraryCharges> response = new ApiResponse<>(1500,
                    "Failed to retrieve library chargwa", null);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping
    public ResponseEntity<ApiResponse<LibraryCharges>> updateLibraryCharges(@RequestBody LibraryCharges libraryCharges){
        try {
            LibraryCharges updatedCharge = libraryChargesService
                    .updateLibraryCharges(libraryCharges);

            ApiResponse<LibraryCharges> response = new ApiResponse<>(1201,
                    "Library charges updated successfully", updatedCharge);
            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (Exception e) {
            ApiResponse<LibraryCharges> response = new ApiResponse<>(1500,
            "Failed to update library charges", null);
    return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
}
