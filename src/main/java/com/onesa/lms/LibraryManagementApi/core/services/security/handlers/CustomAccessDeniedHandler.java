package com.onesa.lms.LibraryManagementApi.core.services.security.handlers;

import java.io.IOException;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.onesa.lms.LibraryManagementApi.core.utils.dtos.ApiResponse;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@Component
public class CustomAccessDeniedHandler  implements AccessDeniedHandler{

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response,
            AccessDeniedException accessDeniedException) throws IOException, ServletException {
        response.setStatus(HttpServletResponse.SC_FORBIDDEN); // 403
        response.setContentType("application/json");

        ApiResponse<String> apiResponse = new ApiResponse<>(
                403,
                "You are not allowed to access this resource",
                null
        );

        response.getWriter().write(objectMapper.writeValueAsString(apiResponse));
    }
    
}
