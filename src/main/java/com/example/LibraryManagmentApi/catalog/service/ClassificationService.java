package com.example.LibraryManagmentApi.catalog.service;

import java.util.List;
import org.springframework.stereotype.Service;

import com.example.LibraryManagmentApi.catalog.models.Classification;
import com.example.LibraryManagmentApi.catalog.repository.ClassificationRepository;

@Service
public class ClassificationService {
    public ClassificationRepository classificationRepository;

    // save Classification
    public Classification saveClassification(Classification classification){
        return classificationRepository.save(classification);
    }


    //  Get a Classification by its ID
    public Classification getClassificationById(int id){
        return classificationRepository.findById(id).
            orElseThrow(() -> new RuntimeException("Classification not found with id " + id));
    }
    
    public void deleteClassification(int id) {
        if (!classificationRepository.existsById(id)) {
            throw new RuntimeException("Classification not found with id " + id);
        }
        classificationRepository.deleteById(id);
    }

    public List<Classification> getAllClassifications(){
        return classificationRepository.findAll();
    }
}
