package com.onesa.lms.LibraryManagementApi.domain.shelf.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.onesa.lms.LibraryManagementApi.domain.shelf.model.ShelfBook;

public interface ShelfBookRepository extends JpaRepository<ShelfBook, Long> {
    
    ShelfBook findShelfBookById(long id);

    List<ShelfBook> findByBookId(long bookId);
    
}
