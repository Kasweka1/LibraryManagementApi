package com.onesa.lms.LibraryManagementApi.domain.shelf.model;



import com.onesa.lms.LibraryManagementApi.domain.catalog.models.Section;

import jakarta.persistence.Column;
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

    @Column(nullable= false)
    private String location;

    @Column(nullable= false)
    private int numberOfRows;

    @Column(nullable= false)
    private String material;

    @Column(nullable= false)
    private String shelfCondition;

    @Column(nullable= false)
    private boolean statusAvailability;

    @ManyToOne
    @JoinColumn(name="section_id", nullable = false)
    private Section section;

}
