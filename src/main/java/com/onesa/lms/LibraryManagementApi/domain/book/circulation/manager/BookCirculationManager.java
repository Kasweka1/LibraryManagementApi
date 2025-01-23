package com.onesa.lms.LibraryManagementApi.domain.book.circulation.manager;

import java.time.LocalDate;
import java.util.List;

import com.onesa.lms.LibraryManagementApi.core.services.user.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onesa.lms.LibraryManagementApi.core.services.user.repository.UserRepository;
import com.onesa.lms.LibraryManagementApi.core.services.user.service.UserService;
import com.onesa.lms.LibraryManagementApi.domain.book.circulation.model.BookCirculation;
import com.onesa.lms.LibraryManagementApi.domain.book.circulation.model.util.LendStatus;
import com.onesa.lms.LibraryManagementApi.domain.book.circulation.repository.BookCirculationRepository;
import com.onesa.lms.LibraryManagementApi.domain.book.circulation.service.BookCirculationService;
import com.onesa.lms.LibraryManagementApi.domain.book.management.model.Book;
import com.onesa.lms.LibraryManagementApi.domain.book.management.repository.BookRespository;
import com.onesa.lms.LibraryManagementApi.domain.catalog.models.Author;
import com.onesa.lms.LibraryManagementApi.domain.catalog.models.Classification;
import com.onesa.lms.LibraryManagementApi.domain.catalog.models.Publisher;
import com.onesa.lms.LibraryManagementApi.domain.catalog.models.Section;
import com.onesa.lms.LibraryManagementApi.domain.library.models.LibraryConfigurations;
import com.onesa.lms.LibraryManagementApi.domain.library.repository.LibraryConfigurationsRepository;

@Service
public class BookCirculationManager implements BookCirculationService {

    @Autowired
    private BookCirculationRepository bookCirculationRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private BookRespository bookRespository;

    @Autowired
    private LibraryConfigurationsRepository libraryConfigurationsRepository;

    @Override
    public BookCirculation lendBook(Long bookId, Long memberId) {

        Book book = bookRespository.findBookById(bookId);
        if (book == null) {
            throw new IllegalArgumentException("Book not found");
        }

        if (!isBookAvailable(bookId)) {
            throw new IllegalArgumentException("Book is not available");
        }

        User member = userRepository.findUserById(memberId);
        if (member == null) {
            throw new IllegalArgumentException("Member not found");
        }

        User librarian = userService.getLoggedInUser();
        if (librarian == null) {
            throw new IllegalArgumentException("Librarian not found");
        }

        // Reducee the number of copies of the book
        book.setNumberOfCopies(book.getNumberOfCopies() - 1);
        bookRespository.save(book);

        // Get a return date
        int returnDays = getReturnDays(bookId);
        LocalDate dueDate = LocalDate.now().plusDays(returnDays);

        BookCirculation bookLend = BookCirculation.builder()
                .book(bookRespository.findBookById(bookId))
                .borrower(userRepository.findUserById(memberId))
                .librarianLender(librarian)
                .lendDate(LocalDate.now())
                .dueDate(dueDate)
                .status(LendStatus.BORROWED)
                .build();

        return bookCirculationRepository.save(bookLend);
    }

    @Override
    public BookCirculation returnBook(Long lendId) {
        BookCirculation lentBook = bookCirculationRepository.findBookCirculationById(lendId);
        if (lentBook == null) {
            throw new IllegalArgumentException("Book not found");
        }

        if (lentBook.getStatus() == LendStatus.RETURNED) {
            throw new IllegalArgumentException("Book has already been returned");
        }

        User librarian = userService.getLoggedInUser();
        if (librarian == null) {
            throw new IllegalArgumentException("Librarian not found");
        }

        // Set the Rreturn date and the librarian who returned the book
        lentBook.setReturnDate(LocalDate.now());
        lentBook.setLibrarianReturner(librarian);
        lentBook.setStatus(LendStatus.RETURNED);
        

        // Increase book copies
        Book book = lentBook.getBook();
        book.setNumberOfCopies(book.getNumberOfCopies() + 1);
        bookRespository.save(book);

        return bookCirculationRepository.save(lentBook);
    }

    @Override
    public BookCirculation getBookCirculationById(Long id) {
        return bookCirculationRepository.findBookCirculationById(id);
    }

    @Override
    public BookCirculation getBookCirculationByBookId(Long bookId) {
        return bookCirculationRepository.findBookCirculationByBook(bookRespository.findBookById(bookId));
    }

    // Todo : implmennt get by member id like ht eth eone on the card not he id for
    // the database
    @Override
    public BookCirculation getBookCirculationByMemberId(Long memberId) {
        return bookCirculationRepository.findBookCirculationByBorrower(userRepository.findUserById(memberId));
    }

    @Override
    public List<BookCirculation> getBooksCirculatedByLibrarianId(Long librarianId) {
        return bookCirculationRepository.findAllByLibrarianLender(userRepository.findUserById(librarianId));
    }

    @Override
    public <T> List<BookCirculation> getBooksCirculatedByCatalog(T catalog) {
        if (catalog instanceof Author) {
            return bookCirculationRepository.findByBookAuthor((Author) catalog);
        } else if (catalog instanceof Classification) {
            return bookCirculationRepository.findByBookClassification((Classification) catalog);
        } else if (catalog instanceof Publisher) {
            return bookCirculationRepository.findByBookPublisher((Publisher) catalog);
        } else if (catalog instanceof Section) {
            return bookCirculationRepository.findByBookSection((Section) catalog);
        } else {
            throw new IllegalArgumentException("Unsupported catalog type: " + catalog.getClass().getName());
        }
    }

    @Override
    public boolean isBookAvailable(Long bookId) {
        Book book = bookRespository.findBookById(bookId);
        if (book == null) {
            throw new IllegalArgumentException("Book not found");
        }

        return book.getNumberOfCopies() > 0;
    }

    @Override
    public int getReturnDays(Long bookId) {
        LibraryConfigurations config = libraryConfigurationsRepository.findFirstByOrderById();
        return config != null ? config.getDefaultReturnPeriod() : 7; // Default to 7 days if no configuration
    }

    @Override
    public BookCirculation getBookCirculationByLibraryId(String libraryId) {
       return bookCirculationRepository.findBookCirculationByBorrower(userRepository.findByLibraryIdNumber(libraryId));
    }

    @Override
    public BookCirculation getBookCirculationByLendStatus(LendStatus status) {
        return bookCirculationRepository.findBookCirculationByStatus(status);
    }

}
