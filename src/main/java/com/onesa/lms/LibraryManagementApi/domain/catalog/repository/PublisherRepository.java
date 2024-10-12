package com.onesa.lms.LibraryManagementApi.domain.catalog.repository;

import com.onesa.lms.LibraryManagementApi.domain.catalog.models.Publisher;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PublisherRepository extends JpaRepository<Publisher, Long>{

    Publisher findByName(String name);
    Publisher findPublisherById(Long id);
    
}
