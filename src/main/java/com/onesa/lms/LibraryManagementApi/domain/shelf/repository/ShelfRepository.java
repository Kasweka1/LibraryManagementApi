package com.onesa.lms.LibraryManagementApi.domain.shelf.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.onesa.lms.LibraryManagementApi.domain.catalog.models.Section;
import com.onesa.lms.LibraryManagementApi.domain.shelf.model.Shelf;

@Repository
public interface ShelfRepository extends JpaRepository<Shelf,Long>{
    Shelf findBySection(Section section);

    Shelf findShelfById(long id);
}
