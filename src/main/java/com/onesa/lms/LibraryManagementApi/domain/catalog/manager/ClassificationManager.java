package com.onesa.lms.LibraryManagementApi.domain.catalog.manager;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onesa.lms.LibraryManagementApi.domain.catalog.models.Classification;
import com.onesa.lms.LibraryManagementApi.domain.catalog.models.Section;
import com.onesa.lms.LibraryManagementApi.domain.catalog.repository.ClassificationRepository;
import com.onesa.lms.LibraryManagementApi.domain.catalog.service.ClassificationService;

@Service
public class ClassificationManager implements ClassificationService {

    @Autowired
    public ClassificationRepository classificationRepository;

    @Override
    public Classification createClassification(Classification classification) {
        return  classificationRepository.save(classification);
    }

    @Override
    public List<Classification> getAllClassifications() {
        return classificationRepository.findAll();
    }

    @Override
    public Classification getClassificationById(long id) {
        return classificationRepository.findClassificationById(id);
    }

    @Override
    public Classification updateClassification(long id, Classification classification) {
        Classification existingClassification = classificationRepository.findClassificationById(id);
        existingClassification.setName(classification.getName());
        existingClassification.setSection(classification.getSection());
        return classificationRepository.save(existingClassification);

    }

    @Override
    public boolean deleteClassification(long id) {
        classificationRepository.deleteById(id);
        return false;
    }

    @Override
    public List<Classification> findClassificationsBySection(Section section) {
        return classificationRepository.findClassificationBySection(section);
       
    }
    
}
