package com.onesa.lms.LibraryManagementApi.domain.library.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LibraryCharges {
    
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private long id;
    private String subscriptionFee;
    private String overdueFine;
    private String pentaltyFee;

}
