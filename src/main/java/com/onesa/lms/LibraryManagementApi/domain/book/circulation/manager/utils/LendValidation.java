package com.onesa.lms.LibraryManagementApi.domain.book.circulation.manager.utils;

import com.onesa.lms.LibraryManagementApi.core.services.user.models.User;
import com.onesa.lms.LibraryManagementApi.domain.book.circulation.repository.BookCirculationRepository;
import com.onesa.lms.LibraryManagementApi.domain.library.models.LibraryConfigurations;
import com.onesa.lms.LibraryManagementApi.domain.library.repository.LibraryConfigurationsRepository;

public class LendValidation {

    private final BookCirculationRepository bookCirculationRepository;
    private final LibraryConfigurationsRepository libraryConfigurationsRepository;

    // Constructor to inject dependencies
    public LendValidation(BookCirculationRepository bookCirculationRepository,
            LibraryConfigurationsRepository libraryConfigurationsRepository) {
        this.bookCirculationRepository = bookCirculationRepository;
        this.libraryConfigurationsRepository = libraryConfigurationsRepository;
    }

    /**
     * Checks if the user is eligible to borrow a book.
     *
     * @param user the user to validate
     * @return true if the user can borrow, false otherwise
     */
    private void validateUserRoleAndStatus(User user) {
        if (!isMemberOrLibrarian(user)) {
            throw new IllegalArgumentException("User is not a member or librarian.");
        }
        if (!isUserActive(user)) {
            throw new IllegalArgumentException("User account is not active.");
        }
    }

    public void validateBorrowEligibility(User user) {
        validateUserRoleAndStatus(user);
        validateBorrowLimit(user);
    }

    /**
     * Validates that the user has not exceeded the borrow limit.
     */
    private void validateBorrowLimit(User user) {
        int borrowLimit = getBorrowLimit();
        int currentlyBorrowedBooks = bookCirculationRepository.countByBorrowerAndReturnDateIsNull(user);

        if (currentlyBorrowedBooks >= borrowLimit) {
            throw new IllegalArgumentException(
                    "Borrow limit exceeded. You cannot borrow more than " + borrowLimit + " books.");
        }
    }

    /**
     * Checks if the user is a member or librarian.
     *
     * @param user the user to validate
     * @return true if the user is a member or librarian
     */
    private boolean isMemberOrLibrarian(User user) {
        String role = user.getRole().toString();
        return "MEMBER".equals(role) || "LIBRARIAN".equals(role);
    }

    /**
     * Checks if the user's account is active.
     *
     * @param user the user to validate
     * @return true if the user is active
     */
    private boolean isUserActive(User user) {
        return "ACTIVE".equals(user.getUserStatus().toString());
    }

    private int getBorrowLimit() {
        LibraryConfigurations config = libraryConfigurationsRepository.findFirstByOrderById();
        return config != null ? config.getMaxBorrowLimit() : 1; // Default to 5 days if no configuration
    }
}
