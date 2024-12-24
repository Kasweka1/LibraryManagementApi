package com.onesa.lms.LibraryManagementApi.domain.shelf.manager;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onesa.lms.LibraryManagementApi.domain.catalog.models.Section;
import com.onesa.lms.LibraryManagementApi.domain.catalog.repository.SectionRepository;
import com.onesa.lms.LibraryManagementApi.domain.shelf.model.Shelf;
import com.onesa.lms.LibraryManagementApi.domain.shelf.repository.ShelfRepository;
import com.onesa.lms.LibraryManagementApi.domain.shelf.service.ShelfService;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ShelfManager implements ShelfService {

    @Autowired
    private ShelfRepository shelfRepository;

    @Autowired
    private SectionRepository sectionRepository;

    @Override
    public Shelf createShelf(Shelf shelf) {
     return shelfRepository.save(shelf);
    }

    @Override
    public List<Shelf> getAllShelves() {
       return shelfRepository.findAll();
    }

    @Override
    public Shelf getShelfById(long id) {
      return shelfRepository.findShelfById(id);
    }

    @Override
    public Shelf updateShelf(long id, Shelf shelf) {

      Shelf existingShelf = shelfRepository.findShelfById(id);

      if (existingShelf == null) {
          throw new EntityNotFoundException("Shelf with id " + id + " not found.");
      }
  
      Section section = sectionRepository.findSectionById(shelf.getSection().getId());

       existingShelf.setLocation(shelf.getLocation());
       existingShelf.setShelfCondition(shelf.getShelfCondition());
       existingShelf.setMaterial(shelf.getMaterial());
       existingShelf.setNumberOfRows(shelf.getNumberOfRows());
       existingShelf.setStatusAvailability(shelf.isStatusAvailability());
       existingShelf.setSection(section);

       return shelfRepository.save(existingShelf);

    }

    @Override
    public boolean deleteShelf(long id) {
      shelfRepository.deleteById(id);
      return false;
    }
    
}
