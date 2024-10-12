package com.onesa.lms.LibraryManagementApi.domain.catalog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.onesa.lms.LibraryManagementApi.domain.catalog.models.Section;

@Repository
public interface SectionRepository extends JpaRepository<Section, Long>{

    Section findByName (String name);

    Section findSectionById(long id);
}
