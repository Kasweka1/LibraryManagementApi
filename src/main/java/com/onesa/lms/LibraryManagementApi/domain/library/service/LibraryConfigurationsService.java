package com.onesa.lms.LibraryManagementApi.domain.library.service;

import com.onesa.lms.LibraryManagementApi.domain.library.models.LibraryConfigurations;

public interface LibraryConfigurationsService {
    
    LibraryConfigurations getLibraryConfigurations();

    LibraryConfigurations updateLibraryConfigurations(LibraryConfigurations libraryConfigurations);

    public void deleteLibraryConfigurations(Long id);
    
}
