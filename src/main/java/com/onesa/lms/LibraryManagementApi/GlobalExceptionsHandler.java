package com.onesa.lms.LibraryManagementApi;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.onesa.lms.LibraryManagementApi.core.dtos.ApiResponse;

@ControllerAdvice
public class GlobalExceptionsHandler {


    //  This is for Gender nums in the Author Model in the catalog
    @ExceptionHandler(InvalidFormatException.class)
    public ResponseEntity<ApiResponse<?>> handleInvalidEnumException(InvalidFormatException e) {
        String errorMessage = "Invalid value for Gender. Accepted values are: MALE, FEMALE.";
        ApiResponse<?> response = new ApiResponse<>(1201, errorMessage, null);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
