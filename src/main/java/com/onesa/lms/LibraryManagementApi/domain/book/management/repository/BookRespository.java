package com.onesa.lms.LibraryManagementApi.domain.book.management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.onesa.lms.LibraryManagementApi.domain.book.management.model.Book;


@Repository
public interface BookRespository extends JpaRepository<Book, Long>{
    Book findByTitle(String title);

    Book findBookById(long id);

    Book findByBookId(String bookId);

}

