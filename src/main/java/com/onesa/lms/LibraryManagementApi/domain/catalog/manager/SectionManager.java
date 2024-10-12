package com.onesa.lms.LibraryManagementApi.domain.catalog.manager;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onesa.lms.LibraryManagementApi.domain.catalog.models.Section;
import com.onesa.lms.LibraryManagementApi.domain.catalog.repository.SectionRepository;
import com.onesa.lms.LibraryManagementApi.domain.catalog.service.SectionService;

@Service
public class SectionManager implements SectionService {

    @Autowired
    private SectionRepository sectionRepository;

    @Override
    public Section createSection(Section section) {
        return sectionRepository.save(section);
    }

    @Override
    public List<Section> getAllSections() {
        return sectionRepository.findAll();
    }

    @Override
    public Section getSectionById(long id) {
        return sectionRepository.findSectionById(id);
    }

    @Override
    public Section updateSection(long id, Section section) {
        Section existingSection = sectionRepository.findSectionById(id);
        existingSection.setName(section.getName());
        existingSection.setDescription(section.getDescription());
        return sectionRepository.save(existingSection);
    }

    @Override
    public boolean deleteSection(long id) {
        sectionRepository.deleteById(id);
        return false;
    }

    
    
}
