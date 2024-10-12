package com.onesa.lms.LibraryManagementApi.domain.catalog.service;

import java.util.List;

import com.onesa.lms.LibraryManagementApi.domain.catalog.models.Section;

public interface SectionService {

    // Create Section
    Section createSection(Section section);

    // Get All Sections
    List<Section> getAllSections();

    // Get Section by Id
    Section getSectionById(long id);

    // Update Section
    Section updateSection(long id, Section section);

    // Delete Section
    boolean deleteSection(long id);
}
