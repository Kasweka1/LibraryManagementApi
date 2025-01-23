package com.onesa.lms.LibraryManagementApi.domain.book.circulation.service;

import java.util.List;

import com.onesa.lms.LibraryManagementApi.domain.book.circulation.model.BookCirculation;
import com.onesa.lms.LibraryManagementApi.domain.book.circulation.model.util.LendStatus;

public interface BookCirculationService {
    
     /**
     * Lend a book to a member.
     * 
     * @param bookId       The ID of the book being lent.
     * @param memberId     The ID of the member borrowing the book.
     * @param librarianId  The ID of the librarian approving the lending.
     * @return A BookLend object containing lending details.
     * @throws IllegalArgumentException if the book is not available or member is not eligible.
     */

    BookCirculation lendBook(Long bookId, Long memberId);

    /**
     * Return a book to the library.
     * 
     * @param bookId       The ID of the book being returned.
     * @param librarianId  The ID of the librarian approving the return.
     * @return A BookLend object containing lending details.
     * @throws IllegalArgumentException if the book is not available or member is not eligible.
     */

    BookCirculation returnBook(Long lendId);

    /**
     * Get a list of all books lent to a member.
     * @param id The ID of the book
     * @return A list of BookLend objects containing lending details.
     * @throws IllegalArgumentException if the book has not been lent to the member before
     * 
     */

    BookCirculation getBookCirculationById(Long id);


    /**
     * Get a list of all books lent to a member.
     * @param bookId The ID of the book
     * @return A list of BookLend objects containing lending details.
     * @throws IllegalArgumentException if the book hasnt being lent to the member before
     * 
     */

    BookCirculation getBookCirculationByBookId(Long bookId);

    /**
     * Get a list of all books lent to a member.
     * @param memberId The ID of the member
     * @return A list of BookLend objects containing lending details.
     * @throws IllegalArgumentException if the book hasnt being lent to the member before
     * 
     */

    BookCirculation getBookCirculationByMemberId(Long memberId);

    /**
     * Get a list of all books lent to a member.
     * @param librarianId The ID of the librarian
     * @return A list of BookLend objects containing lending details.
     * @throws IllegalArgumentException if the book hasnt being lent to the member before
     * 
     */


    List<BookCirculation> getBooksCirculatedByLibrarianId(Long librarianId);


    /**
     * Get a list of all books lent to a member.
     * @param <T> The type of the catalog
     * @param catalog The catalog of the book
     * @return A list of BookLend objects containing lending details.
     */
    <T>List<BookCirculation> getBooksCirculatedByCatalog(T catalog);

    /**
     * Check if a book is available for lending.
     * @param bookId The ID of the book
     * @return true if the book is available, false otherwise
     */

    boolean isBookAvailable(Long bookId);

    /**
     * Get the number of days a book can be borrowed for.
     * @param bookId The ID of the book
     * @return The number of days the book can be borrowed for
     */

    int getReturnDays(Long bookId);


    BookCirculation getBookCirculationByLibraryId(String libraryId);

    BookCirculation getBookCirculationByLendStatus(LendStatus status);


}
