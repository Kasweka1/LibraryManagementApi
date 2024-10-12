package com.onesa.lms.LibraryManagementApi.domain.catalog.manager;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onesa.lms.LibraryManagementApi.domain.catalog.models.Publisher;
import com.onesa.lms.LibraryManagementApi.domain.catalog.repository.PublisherRepository;
import com.onesa.lms.LibraryManagementApi.domain.catalog.service.PublisherService;

@Service
public class PublisherManager implements PublisherService{

    @Autowired
    private PublisherRepository publisherRepository;


    @Override
    public Publisher createPublisher(Publisher publisher) {
        return publisherRepository.save(publisher);
    }

    @Override
    public List<Publisher> getAllPublisher() {
      return publisherRepository.findAll();
    }

    @Override
    public Publisher getPublisherById(long id) {
        return publisherRepository.findPublisherById(id);
    }

    @Override
    public Publisher updatePublisher(long id, Publisher publisher) {
     Publisher existingPublisher = publisherRepository.findPublisherById(id);
     existingPublisher.setName(publisher.getName());
     existingPublisher.setAddress(publisher.getAddress());
     return publisherRepository.save(existingPublisher);
    }

    @Override
    public boolean deletePublisher(long id) {
        publisherRepository.deleteById(id);
        return false;
    }
    
}
