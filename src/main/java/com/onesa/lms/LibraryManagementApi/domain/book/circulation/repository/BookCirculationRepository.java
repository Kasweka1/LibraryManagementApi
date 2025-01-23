package com.onesa.lms.LibraryManagementApi.domain.book.circulation.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.onesa.lms.LibraryManagementApi.core.services.user.models.User;
import com.onesa.lms.LibraryManagementApi.domain.book.circulation.model.BookCirculation;
import com.onesa.lms.LibraryManagementApi.domain.book.circulation.model.util.LendStatus;
import com.onesa.lms.LibraryManagementApi.domain.book.management.model.Book;
import com.onesa.lms.LibraryManagementApi.domain.catalog.models.Author;
import com.onesa.lms.LibraryManagementApi.domain.catalog.models.Classification;
import com.onesa.lms.LibraryManagementApi.domain.catalog.models.Publisher;
import com.onesa.lms.LibraryManagementApi.domain.catalog.models.Section;

@Repository
public interface BookCirculationRepository extends JpaRepository<BookCirculation, Long>{
    
    BookCirculation findBookCirculationById(long id);

    BookCirculation findBookCirculationByBook(Book book);

    BookCirculation findBookCirculationByBorrower(User user);

    List<BookCirculation> findAllByLibrarianLender(User user);

    BookCirculation findBookCirculationByStatus(LendStatus lendStatus);


    // Method to find a BookCirculations by Book Catalogs
    // (These are Author, Classification, Publisher, Section)

    List<BookCirculation> findByBookAuthor(Author author);
    List<BookCirculation> findByBookClassification(Classification classification);
    List<BookCirculation> findByBookPublisher(Publisher publisher);
    List<BookCirculation> findByBookSection(Section section);
}