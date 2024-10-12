package com.onesa.lms.LibraryManagementApi.domain.catalog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.onesa.lms.LibraryManagementApi.domain.catalog.models.Author;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long>{
    Author findByName(String name);

    Author findAuthorById(long id);
}
