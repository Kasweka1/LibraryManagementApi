package com.onesa.lms.LibraryManagementApi.domain.catalog.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponse<T> {
    private int customStatus;
    private String message;
    private T data;
}
