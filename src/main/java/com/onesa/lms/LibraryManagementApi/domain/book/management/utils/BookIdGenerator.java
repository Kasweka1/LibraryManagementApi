package com.onesa.lms.LibraryManagementApi.domain.book.management.utils;

import java.time.Year;

public class BookIdGenerator {

    public static String generateBookId(String libraryUsername, Long id, String section){
        String prefixedLibraryUsername = libraryUsername.length() >= 3 ? libraryUsername.substring(0, 3) : libraryUsername;
        String prefixedSection = section.length() >= 3 ? section.substring(0, 3) : section;
        int year = Year.now().getValue();

        // Format: libraryUsername-year-section-id (e.g., LIB-2024-FIC-00123)
        return String.format("%s-%d-%s-%05d", prefixedLibraryUsername.toUpperCase(), year, prefixedSection.toUpperCase(), id);
    }

}
