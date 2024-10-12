package com.onesa.lms.LibraryManagementApi.domain.book.management.model;

import java.util.Date;

import com.onesa.lms.LibraryManagementApi.domain.catalog.models.Author;
import com.onesa.lms.LibraryManagementApi.domain.catalog.models.Classification;
import com.onesa.lms.LibraryManagementApi.domain.catalog.models.Publisher;
import com.onesa.lms.LibraryManagementApi.domain.catalog.models.Section;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Book {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private long id;
    private String title;

    private String editionNumber;
    private int numberOfPages;  
    private String price;
    private String coverImageUrl;
    private String isbn;
    private String bookId;
    private Date publicationDate;

    @ManyToOne
    @JoinColumn(name = "author_id", nullable = false)
    private Author author;

    @ManyToOne
    @JoinColumn(name = "publisher_id", nullable = false)
    private Publisher publisher;

    @ManyToOne
    @JoinColumn(name = "section_id", nullable = false)
    private Section section;

    @ManyToOne
    @JoinColumn(name = "classification_id", nullable = false)
    private Classification classification;

}
