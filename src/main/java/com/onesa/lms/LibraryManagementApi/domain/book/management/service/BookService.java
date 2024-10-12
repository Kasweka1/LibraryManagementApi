package com.onesa.lms.LibraryManagementApi.domain.book.management.service;

import java.util.List;

import com.onesa.lms.LibraryManagementApi.domain.book.management.model.Book;

public interface BookService {
    Book createBook(Book book);

    List<Book> getAllBooks();

    Book getBookById(long id);

    Book updateBook(Book book ,long id);

    boolean deleteBook(long id);

  
}
