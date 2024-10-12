package com.onesa.lms.LibraryManagementApi.domain.library.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.onesa.lms.LibraryManagementApi.domain.library.models.LibraryCharges;
import com.onesa.lms.LibraryManagementApi.domain.library.repository.LibraryChargesRepository;

import jakarta.annotation.PostConstruct;

@Component
public class StartupLibraryCharge {

    @Autowired
    private LibraryChargesRepository libraryChargesRepository;

    @PostConstruct
    public void init(){
        if(libraryChargesRepository.findFirstByOrderById() == null){
            LibraryCharges defaultConfig = LibraryCharges.builder()
            .overdueFine("100")
            .pentaltyFee("50")
            .subscriptionFee("100")
            .build();

            libraryChargesRepository.save(defaultConfig);
        }
    }
}
