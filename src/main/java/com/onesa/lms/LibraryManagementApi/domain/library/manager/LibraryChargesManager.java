package com.onesa.lms.LibraryManagementApi.domain.library.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onesa.lms.LibraryManagementApi.domain.library.models.LibraryCharges;
import com.onesa.lms.LibraryManagementApi.domain.library.repository.LibraryChargesRepository;
import com.onesa.lms.LibraryManagementApi.domain.library.service.LibraryChargesService;

@Service
public class LibraryChargesManager implements LibraryChargesService {

    @Autowired
    private LibraryChargesRepository libraryChargesRepository;

    @Override
    public LibraryCharges getLibraryCharges() {
       return libraryChargesRepository.findFirstByOrderById();
    }
    @Override
    public LibraryCharges updateLibraryCharges(LibraryCharges libraryCharges) {
       LibraryCharges existingLibraryCharges = libraryChargesRepository.findFirstByOrderById();

       if(existingLibraryCharges != null){
        existingLibraryCharges.setOverdueFine(libraryCharges.getOverdueFine());
        existingLibraryCharges.setPentaltyFee(libraryCharges.getPentaltyFee());
        existingLibraryCharges.setSubscriptionFee(libraryCharges.getSubscriptionFee());
        return libraryChargesRepository.save(existingLibraryCharges);
       }else{
        return libraryChargesRepository.save(libraryCharges);
       }
    }

    @Override
    public void deleteLibraryCharges(Long id) {
        throw new UnsupportedOperationException("Library Charges cannot be deleted.");
    }

}
