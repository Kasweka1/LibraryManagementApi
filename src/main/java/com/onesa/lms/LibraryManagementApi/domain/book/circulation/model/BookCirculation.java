package com.onesa.lms.LibraryManagementApi.domain.book.circulation.model;

import java.time.LocalDate;

import com.onesa.lms.LibraryManagementApi.core.services.user.models.User;
import com.onesa.lms.LibraryManagementApi.domain.book.circulation.model.util.LendStatus;
import com.onesa.lms.LibraryManagementApi.domain.book.management.model.Book;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookCirculation {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private long id;
    
    @ManyToOne
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;

    // User who is borrowing(should have member role)
    @ManyToOne
    @JoinColumn(name = "borrower_id", nullable = false)
    private User borrower;


    // Librarian who lent the borrower(should have role of librarian)
    @ManyToOne
    @JoinColumn(name = "librarian_lender_id", nullable = false)
    private User librarianLender;

    @Column(nullable = false)
    private LocalDate lendDate;

    @Column(nullable = false)
    private LocalDate dueDate;

    // day book was returned
    private LocalDate returnDate;

    // Librarian who returned the Book
    @ManyToOne
    @JoinColumn(name = "librarian_returner_id")
    private User librarianReturner;

    @Enumerated(EnumType.STRING)
    private LendStatus status;


}
