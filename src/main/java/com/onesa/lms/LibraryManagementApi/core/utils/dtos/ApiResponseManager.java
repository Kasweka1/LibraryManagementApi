package com.onesa.lms.LibraryManagementApi.core.utils.dtos;

public class ApiResponseManager {
     // Success response for creating an entity
     public static <T> ApiResponse<T> createEntityResponse(T entity) {
        String entityName = entity.getClass().getSimpleName(); // Get the entity name (like Section, Author)
        String message = entityName + " created successfully";
        return new ApiResponse<>(1100, message, entity); // Status code for creation can start with 1100
    }

    // Success response for updating an entity
    public static <T> ApiResponse<T> updateEntityResponse(T entity) {
        String entityName = entity.getClass().getSimpleName();
        String message = entityName + " updated successfully";
        return new ApiResponse<>(1101, message, entity); // Status code for updating can be 1101
    }

    // Success response for deleting an entity
    public static <T> ApiResponse<Void> deleteEntityResponse(Class<T> entityClass) {
        String entityName = entityClass.getSimpleName();
        String message = entityName + " deleted successfully";
        return new ApiResponse<>(1102, message, null); // Status code for deletion can be 1102
    }

    // Success response for getting an entity
    public static <T> ApiResponse<T> getEntityResponse(T entity) {
        String entityName = entity.getClass().getSimpleName();
        String message = entityName + " retrieved successfully";
        return new ApiResponse<>(1103, message, entity); // Status code for retrieval can be 1103
    }

    // Error response for not finding an entity
    public static <T> ApiResponse<Void> entityNotFoundResponse(Class<T> entityClass) {
        String entityName = entityClass.getSimpleName();
        String message = entityName + " not found";
        return new ApiResponse<>(1404, message, null); // Custom 404-like status code
    }

    // Error response for general failure (optional)
    public static ApiResponse<Void> generalFailureResponse(String message) {
        return new ApiResponse<>(1500, message, null); // Custom 500-like status code
    }
}
