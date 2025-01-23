package com.onesa.lms.LibraryManagementApi.domain.library.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onesa.lms.LibraryManagementApi.domain.library.models.LibraryConfigurations;
import com.onesa.lms.LibraryManagementApi.domain.library.repository.LibraryConfigurationsRepository;
import com.onesa.lms.LibraryManagementApi.domain.library.service.LibraryConfigurationsService;

@Service
public class LibraryConfigurationsManager implements LibraryConfigurationsService {

    @Autowired
    private LibraryConfigurationsRepository libraryConfigurationsRepository;

    @Override
    public LibraryConfigurations getLibraryConfigurations() {
        return libraryConfigurationsRepository.findFirstByOrderById();
    }

    @Override
    public LibraryConfigurations updateLibraryConfigurations(LibraryConfigurations libraryConfigurations) {
        LibraryConfigurations existingConfig = libraryConfigurationsRepository.findFirstByOrderById();

        if (libraryConfigurations.getDefaultReturnPeriod() <= 0) {
            throw new IllegalArgumentException("Return period must be greater than 0");
        }

        if (existingConfig != null) {
            existingConfig.setLibraryName(libraryConfigurations.getLibraryName());
            existingConfig.setUsername(libraryConfigurations.getUsername());
            existingConfig.setPhoneNo(libraryConfigurations.getPhoneNo());
            existingConfig.setEmail(libraryConfigurations.getEmail());
            existingConfig.setWebsite(libraryConfigurations.getWebsite());
            existingConfig.setLocation(libraryConfigurations.getLocation());
            existingConfig.setAddress(libraryConfigurations.getAddress());
            existingConfig.setDefaultReturnPeriod(libraryConfigurations.getDefaultReturnPeriod());

            return libraryConfigurationsRepository.save(existingConfig);
        } else {
            return libraryConfigurationsRepository.save(libraryConfigurations);
        }
    }

    @Override
    public void deleteLibraryConfigurations(Long id) {
        throw new UnsupportedOperationException("Library Configurations cannot be deleted.");
    }

}
