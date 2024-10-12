package com.onesa.lms.LibraryManagementApi.domain.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.onesa.lms.LibraryManagementApi.domain.library.models.LibraryCharges;

@Repository
public interface LibraryChargesRepository extends JpaRepository<LibraryCharges, Long> {
    // LibraryCharges getLibraryChargesById(long id);
    LibraryCharges findFirstByOrderById();
}
