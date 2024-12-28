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

import com.onesa.lms.LibraryManagementApi.core.dtos.ApiResponse;
import com.onesa.lms.LibraryManagementApi.domain.catalog.models.Section;
import com.onesa.lms.LibraryManagementApi.domain.catalog.service.SectionService;

@RestController
@RequestMapping("/sections")
public class SectionController {

    @Autowired
    private  SectionService sectionService;


    @PostMapping
    public ResponseEntity<ApiResponse<Section>> createSection(@RequestBody Section section) {

        try {
            Section createdsection = sectionService.createSection(section);
            ApiResponse<Section> response = new ApiResponse<>(1100, "Section created successfull", createdsection);
            return new ResponseEntity<>(response, HttpStatus.CREATED);

        } catch (Exception e) {
            ApiResponse<Section> response = new ApiResponse<>(1500, "Failed to create section", null);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<Section>>> getAllSections() {
        try {
            List<Section> sections = sectionService.getAllSections();

            if (sections.isEmpty()) {
                ApiResponse<List<Section>> response = new ApiResponse<>(1404, "No sections found", sections);
                return new ResponseEntity<>(response, HttpStatus.OK);
            }

            ApiResponse<List<Section>> response = new ApiResponse<>(1103, "Sections retrieved successfully", sections);
            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (Exception e) {
            ApiResponse<List<Section>> response = new ApiResponse<>(1500, "Failed to retrieve sections", null);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Section>> getSection(@PathVariable long id) {

        try {
            Section section = sectionService.getSectionById(id);
            if (section == null) {
                ApiResponse<Section> response = new ApiResponse<>(1105, "Section not found", null);
                return new ResponseEntity<>(response, HttpStatus.OK);
            }

            ApiResponse<Section> response = new ApiResponse<>(1104, "Section found", section);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            ApiResponse<Section> response = new ApiResponse<>(1120, "An error occurred", null);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Section>> updateSection(@PathVariable long id,
            @RequestBody Section updatedSection) {
        try {
            Section section = sectionService.updateSection(id, updatedSection);

            if (section == null) {
                ApiResponse<Section> response = new ApiResponse<>(1105, "Section not found", null);
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }

            ApiResponse<Section> response = new ApiResponse<>(1107, "Section updated successfully", section);
            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (Exception e) {
            ApiResponse<Section> response = new ApiResponse<>(1120, "Failed to update section", null);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteSection(@PathVariable long id) {
        try {
            boolean deleted = sectionService.deleteSection(id);

            if (!deleted) {
                ApiResponse<Void> response = new ApiResponse<>(1001, "Section not found", null);
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }

            ApiResponse<Void> response = new ApiResponse<>(1000, "Section deleted successfully", null);
            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (Exception e) {
            ApiResponse<Void> response = new ApiResponse<>(1120, "Failed to delete section", null);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
