package com.onesa.lms.LibraryManagementApi.domain.shelf.model;

import com.onesa.lms.LibraryManagementApi.domain.book.management.model.Book;
import com.onesa.lms.LibraryManagementApi.domain.catalog.models.Section;

import jakarta.annotation.Generated;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ShelfBook {
    
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private long id;

    @ManyToOne
    @JoinColumn(name="book_id", nullable=false)
    private Book book;

    @ManyToOne
    @JoinColumn(name="shelf_id", nullable=false)
    private Shelf shelf;

    @Transient
    public Section getSection(){
        return this.shelf != null ? this.shelf.getSection() : null;
    }

}
