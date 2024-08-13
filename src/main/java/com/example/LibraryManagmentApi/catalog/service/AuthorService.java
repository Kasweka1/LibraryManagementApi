package com.example.LibraryManagmentApi.catalog.service;

import org.springframework.stereotype.Service;
import java.util.List;
import com.example.LibraryManagmentApi.catalog.models.Author;
import com.example.LibraryManagmentApi.catalog.repository.AuthorRepository;

@Service
public class AuthorService {
    public AuthorRepository authorRepository;

    public Author saveAuthor(Author author){
        return authorRepository.save(author);
    }

    public Author getAuthorById(int id){
        return authorRepository.findById(id).
            orElseThrow(() -> new RuntimeException("Author not found with id " + id));
    }

    public void deleteAuthor(int id){
        if (!authorRepository.existsById(id)) {
            throw new RuntimeException("Author not found with id " + id);
        }
        authorRepository.deleteById(id);

    }

    public List<Author> getAllAuthors(){
        return authorRepository.findAll();
    }
}
