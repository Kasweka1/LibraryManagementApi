package com.example.LibraryManagmentApi.catalog.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.LibraryManagmentApi.catalog.models.Section;

import com.example.LibraryManagmentApi.catalog.repository.SectionRepository;

@Service
public class SectionService {
    
    @Autowired
    private SectionRepository sectionRepository;

    public Section saveSection(Section section){
        return sectionRepository.save(section);
    }

    // Get a section by its ID
    public Section getSectionById(int id) {
        return sectionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Section not found with id " + id));
    }

    // Delete a section by its ID
    public void deleteSection(int id) {
        if (!sectionRepository.existsById(id)) {
            throw new RuntimeException("Section not found with id " + id);
        }
        sectionRepository.deleteById(id);
    }

    // Get all sections
    public List<Section> getAllSections() {
        return sectionRepository.findAll();
    }

}
