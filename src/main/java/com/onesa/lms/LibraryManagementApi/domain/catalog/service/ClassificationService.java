package com.onesa.lms.LibraryManagementApi.domain.catalog.service;

import java.util.List;

import com.onesa.lms.LibraryManagementApi.domain.catalog.models.Classification;
import com.onesa.lms.LibraryManagementApi.domain.catalog.models.Section;

public interface ClassificationService {
    // Create a Classfication
    Classification createClassification(Classification classification);

    // List of all Classifications
    List<Classification> getAllClassifications();

    // Get Classification By id
    Classification getClassificationById(long id);

    // Update Classification
    Classification updateClassification(long id, Classification classification);

    // Delete Classification
    boolean deleteClassification(long id);

    // List of  Classification by Section
    List<Classification> findClassificationsBySection(Section section);
}
