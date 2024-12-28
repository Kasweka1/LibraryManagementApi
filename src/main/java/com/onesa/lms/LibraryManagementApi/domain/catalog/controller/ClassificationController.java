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
import com.onesa.lms.LibraryManagementApi.domain.catalog.models.Classification;
import com.onesa.lms.LibraryManagementApi.domain.catalog.models.Section;
import com.onesa.lms.LibraryManagementApi.domain.catalog.service.ClassificationService;
import com.onesa.lms.LibraryManagementApi.domain.catalog.service.SectionService;

@RestController
@RequestMapping("/classifications")
public class ClassificationController {

    @Autowired
    public ClassificationService classificationService;

    @Autowired
    public SectionService sectionService;

    @PostMapping
    public ResponseEntity<ApiResponse<Classification>> createClassification(
            @RequestBody Classification classification) {
        try {
            Classification createdClassification = classificationService.createClassification(classification);
            ApiResponse<Classification> response = new ApiResponse<>(1100, "Classification created successfull",
                    createdClassification);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            ApiResponse<Classification> response = new ApiResponse<>(1500, "Failed to create Classification", null);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<Classification>>> getAllClassifications() {
        try {
            List<Classification> classifications = classificationService.getAllClassifications();

            if (classifications.isEmpty()) {
                ApiResponse<List<Classification>> response = new ApiResponse<>(1404, "No Classifications found",
                        classifications);
                return new ResponseEntity<>(response, HttpStatus.OK);
            }

            ApiResponse<List<Classification>> response = new ApiResponse<>(1103,
                    "Classifications retrieved successfully", classifications);
            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (Exception e) {
            ApiResponse<List<Classification>> response = new ApiResponse<>(1500, "Failed to retrieve Classifications",
                    null);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Classification>> getClassification(@PathVariable long id) {

        try {
            Classification classification = classificationService.getClassificationById(id);
            if (classification == null) {
                ApiResponse<Classification> response = new ApiResponse<>(1105, "Classification not found", null);
                return new ResponseEntity<>(response, HttpStatus.OK);
            }

            ApiResponse<Classification> response = new ApiResponse<>(1104, "Classification found", classification);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            ApiResponse<Classification> response = new ApiResponse<>(1120, "An error occurred", null);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Classification>> updateClassification(@PathVariable long id,
            @RequestBody Classification updatedClassification) {
        try {
            Classification classification = classificationService.updateClassification(id, updatedClassification);

            if (classification == null) {
                ApiResponse<Classification> response = new ApiResponse<>(1105, "Classification not found", null);
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }

            ApiResponse<Classification> response = new ApiResponse<>(1107, "Classification updated successfully",
                    classification);
            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (Exception e) {
            ApiResponse<Classification> response = new ApiResponse<>(1120, "Failed to update Classification", null);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteClassification(@PathVariable long id) {
        try {
            boolean deleted = classificationService.deleteClassification(id);

            if (!deleted) {
                ApiResponse<Void> response = new ApiResponse<>(1001, "Classification not found", null);
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }

            ApiResponse<Void> response = new ApiResponse<>(1000, "Classification deleted successfully", null);
            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (Exception e) {
            ApiResponse<Void> response = new ApiResponse<>(1120, "Failed to delete Classification", null);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Getting Classifciation By Section
    @GetMapping("/section/{sectionId}")
    public ResponseEntity<ApiResponse<List<Classification>>> getClassificationBySection(@PathVariable long sectionId) {
        Section section = sectionService.getSectionById(sectionId);
        
        if (section == null) {
            ApiResponse<List<Classification>> response = new ApiResponse<>(1204, "Section not found", null);
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
        
        String sectionName = section.getName(); 
        List<Classification> classifications = classificationService.findClassificationsBySection(section);

        ApiResponse<List<Classification>> response = new ApiResponse<>(1205, "Classifications for "  + sectionName + " Section found", classifications);
        return new ResponseEntity<>(response, HttpStatus.OK);

    }
}