package com.onesa.lms.LibraryManagementApi.domain.catalog.manager;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onesa.lms.LibraryManagementApi.domain.catalog.models.Author;
import com.onesa.lms.LibraryManagementApi.domain.catalog.repository.AuthorRepository;
import com.onesa.lms.LibraryManagementApi.domain.catalog.service.AuthorService;

@Service
public class AuthorManager implements AuthorService{
    
    @Autowired
    private AuthorRepository authorRepository;

    @Override
    public Author createAuthor(Author author) {
        return authorRepository.save(author);
    }

    @Override
    public List<Author> getAllAuthors() {
       return authorRepository.findAll();
    }

    @Override
    public Author getAuthorById(long id) {
       return authorRepository.findAuthorById(id);
    }

    @Override
    public Author updateAuthor(long id, Author author) {
        Author existingAuthor = authorRepository.findAuthorById(id);
        existingAuthor.setName(author.getName());
        existingAuthor.setGender(author.getGender());
        return authorRepository.save(existingAuthor);
    }

    @Override
    public boolean deleteAuthor(long id) {
      authorRepository.deleteById(id);
      return false;
    }
    
}

