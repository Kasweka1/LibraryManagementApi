package com.onesa.lms.LibraryManagementApi.domain.shelf.model;


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

@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Shelf {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private long id;
    private String location;
    private int rows;
    private String material;
    private String condition;
    private boolean statusAvailability;

    @ManyToOne
    @JoinColumn(name = "section_id", nullable = false)
    private Section section; 
}
