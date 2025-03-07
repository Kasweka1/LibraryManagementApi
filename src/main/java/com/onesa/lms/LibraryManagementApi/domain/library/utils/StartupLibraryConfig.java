package com.onesa.lms.LibraryManagementApi.domain.library.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.onesa.lms.LibraryManagementApi.domain.library.models.LibraryConfigurations;
import com.onesa.lms.LibraryManagementApi.domain.library.repository.LibraryConfigurationsRepository;

import jakarta.annotation.PostConstruct;

@Component
public class StartupLibraryConfig {
    @Autowired
    private LibraryConfigurationsRepository libraryConfigurationsRepository;

    @PostConstruct
    public void init() {
        if (libraryConfigurationsRepository.findFirstByOrderById() == null) {
            LibraryConfigurations defaultConfig = LibraryConfigurations.builder()
                .libraryName("Toshokan Library")
                .username("toshokan")
                .phoneNo("123456789")
                .email("library@toshokan.com")
                .website("www.toshokan.com")
                .location("Toshokan City")
                .address("123 toshokan St.")
                .defaultReturnPeriod(7)
                .maxBorrowLimit(1)
                .build();
            
            libraryConfigurationsRepository.save(defaultConfig);
        }
    }
}
