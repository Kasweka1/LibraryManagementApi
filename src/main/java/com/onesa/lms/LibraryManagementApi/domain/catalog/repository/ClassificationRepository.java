package com.onesa.lms.LibraryManagementApi.domain.catalog.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.onesa.lms.LibraryManagementApi.domain.catalog.models.Classification;
import com.onesa.lms.LibraryManagementApi.domain.catalog.models.Section;

@Repository
public interface ClassificationRepository extends JpaRepository<Classification, Long>{
    Classification findByName(String name);

    Classification findClassificationById(long id);

    // Finding Classification by section
    List<Classification> findClassificationBySection(Section section);

}
