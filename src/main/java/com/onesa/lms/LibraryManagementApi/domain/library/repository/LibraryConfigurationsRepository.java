package com.onesa.lms.LibraryManagementApi.domain.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.onesa.lms.LibraryManagementApi.domain.library.models.LibraryConfigurations;

@Repository
public interface LibraryConfigurationsRepository extends JpaRepository<LibraryConfigurations, Long> {
    
    // LibraryConfigurations findLibraryById(long id);
    LibraryConfigurations findFirstByOrderById();

}
