package com.onesa.lms.LibraryManagementApi.domain.library.service;

import com.onesa.lms.LibraryManagementApi.domain.library.models.LibraryCharges;

public interface LibraryChargesService {
    
    LibraryCharges getLibraryCharges();

    LibraryCharges updateLibraryCharges(LibraryCharges libraryCharges);

    public void deleteLibraryCharges(Long id);
}
