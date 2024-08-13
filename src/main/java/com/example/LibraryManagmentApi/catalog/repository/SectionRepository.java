package com.example.LibraryManagmentApi.catalog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.LibraryManagmentApi.catalog.models.Section;

@Repository
public interface SectionRepository extends JpaRepository<Section, Integer> {
    
}
