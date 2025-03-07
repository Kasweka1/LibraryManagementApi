package com.onesa.lms.LibraryManagementApi.domain.book.circulation.manager.utils;

import java.util.regex.Pattern;

import com.onesa.lms.LibraryManagementApi.core.services.user.models.User;
import com.onesa.lms.LibraryManagementApi.core.services.user.repository.UserRepository;

public class MemberIdentifierResolver {
    private final UserRepository userRepository;

    public MemberIdentifierResolver(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Resolves a user based on the provided identifier, which can be either
     * a library ID or a username.
     * 
     * @param identifier the library ID or username
     * @return the User object if found
     * @throws IllegalArgumentException if the user is not found
     */
    public User resolveMember(String identifier) {
        User member = null;

        if (isLibraryId(identifier)) {
            member = userRepository.findByLibraryIdNumber(identifier);
        }

        if (member == null) {
            member = userRepository.findByUsername(identifier);
        }

        if (member == null) {
            throw new IllegalArgumentException("Member not found for identifier: " + identifier);
        }

        return member;
    }

    /**
     * Checks if the identifier matches the expected format of a library ID.
     * 
     * @param identifier the identifier to check
     * @return true if it is a library ID, false otherwise
     */
    private boolean isLibraryId(String identifier) {
        String libraryIdPattern = "^(AD|LI|ME)\\d{4}\\d{5}$"; // Prefix + Year + 5 digits
        return Pattern.matches(libraryIdPattern, identifier);
    }

   
}
