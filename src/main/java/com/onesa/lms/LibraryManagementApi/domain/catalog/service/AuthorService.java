package com.onesa.lms.LibraryManagementApi.domain.catalog.service;

import java.util.List;

import com.onesa.lms.LibraryManagementApi.domain.catalog.models.Author;

public interface AuthorService {

    // Create an Author
    Author createAuthor(Author author);

    //  List of all Authors
    List<Author> getAllAuthors();

    // Get Author By Id
    Author getAuthorById(long id);

    // Update Author
    Author updateAuthor(long id, Author author);

    // Delete Author
    boolean deleteAuthor(long id);
}
