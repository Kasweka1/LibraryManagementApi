package com.onesa.lms.LibraryManagementApi.core.services.user.manager.utilities;

import java.time.Year;

public class LibraryIdGenerator {
    public static String generateLibraryId(String role, Long id) {
        String prefix;
        switch (role) {
            case "ADMIN":
                prefix = "AD";
                break;
            case "LIBRARIAN":
                prefix = "LI";
                break;
            case "MEMBER":
                prefix = "ME";
                break;
            default:
                throw new IllegalArgumentException("Invalid role: " + role);
        }

        String year = String.valueOf(Year.now().getValue());
        String formattedId = String.format("%05d", id); // Pad ID to 5 digits

        return prefix + year + formattedId;
    }
    
}
