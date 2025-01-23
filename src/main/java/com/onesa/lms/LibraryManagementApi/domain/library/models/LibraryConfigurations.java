package com.onesa.lms.LibraryManagementApi.domain.library.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class LibraryConfigurations {
    
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private long id;
    
    private String libraryName;
    private String username;
    private String phoneNo;
    private String email;
    private String website;
    private String location;
    private String address;

     // Default return period in days
    @Column(nullable = false)
    private int defaultReturnPeriod;
}
