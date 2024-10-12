package com.onesa.lms.LibraryManagementApi.domain.catalog.service;

import java.util.List;

import com.onesa.lms.LibraryManagementApi.domain.catalog.models.Publisher;

public interface PublisherService {
    
    // Create a Publisher
    Publisher createPublisher(Publisher publisher);

    // List of all Publishers
    List<Publisher> getAllPublisher(); 

    // Get Publisher By Id
    Publisher getPublisherById(long id);

    // Update Publisher
    Publisher updatePublisher(long id, Publisher publisher);

    // Delete Publisher
    boolean deletePublisher(long id);
}
