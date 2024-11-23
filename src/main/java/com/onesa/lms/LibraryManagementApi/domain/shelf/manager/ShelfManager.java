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


    // TODO: test this method thorougly
    @Override
    public Shelf updateShelf(Shelf shelf, long id) {

       Section section = sectionRepository.findById(shelf.getSection().getId())
                          .orElseThrow(() -> new EntityNotFoundException("Section not found"));
       Shelf existingShelf = shelfRepository.findShelfById(id);
       existingShelf.setLocation(shelf.getLocation());
       existingShelf.setCondition(shelf.getCondition());
       existingShelf.setMaterial(shelf.getMaterial());
       existingShelf.setRows(shelf.getRows());
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
