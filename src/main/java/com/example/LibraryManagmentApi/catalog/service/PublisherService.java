package com.example.LibraryManagmentApi.catalog.service;

import org.springframework.stereotype.Service;
import java.util.List;
import com.example.LibraryManagmentApi.catalog.models.Publisher;
import com.example.LibraryManagmentApi.catalog.models.Section;
import com.example.LibraryManagmentApi.catalog.repository.PublisherRepository;

@Service
public class PublisherService {
    
    public PublisherRepository publisherRepository;

    public Publisher savePublisher(Publisher publisher){
        return publisherRepository.save(publisher);
    }

    public Publisher getPublisherById(int id){
        return publisherRepository.findById(id).
            orElseThrow(() -> new RuntimeException("Publisher not found with id " + id));
    }

      // Delete a section by its ID
    public void deletePublisher(int id) {
        if (!publisherRepository.existsById(id)) {
            throw new RuntimeException("Publisher not found with id " + id);
        }
        publisherRepository.deleteById(id);
    }

    // Get all sections
    public List<Publisher> getAllPublishers() {
        return publisherRepository.findAll();
    }

}
