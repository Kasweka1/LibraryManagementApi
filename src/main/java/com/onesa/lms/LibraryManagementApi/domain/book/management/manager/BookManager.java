package com.onesa.lms.LibraryManagementApi.domain.book.management.manager;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onesa.lms.LibraryManagementApi.domain.book.management.model.Book;
import com.onesa.lms.LibraryManagementApi.domain.book.management.repository.BookRespository;
import com.onesa.lms.LibraryManagementApi.domain.book.management.service.BookService;
import com.onesa.lms.LibraryManagementApi.domain.book.management.utils.BookIdGenerator;
import com.onesa.lms.LibraryManagementApi.domain.catalog.models.Section;
import com.onesa.lms.LibraryManagementApi.domain.catalog.service.SectionService;
import com.onesa.lms.LibraryManagementApi.domain.library.models.LibraryConfigurations;
import com.onesa.lms.LibraryManagementApi.domain.library.repository.LibraryConfigurationsRepository;

@Service
public class BookManager implements BookService {

    @Autowired
    private BookRespository bookRespository;

    @Autowired
    private LibraryConfigurationsRepository libraryConfigurationRepository;

    @Autowired
    private SectionService sectionService;

    @Override
    public Book createBook(Book book) {
        // Get Library by Id
        LibraryConfigurations libraryconfig = libraryConfigurationRepository.findFirstByOrderById();

        // Section section = sectionService.getSectionById(book.getSection().getId());
        // Generate bookId
        if(libraryconfig != null){
            String libraryUsername = libraryconfig.getUsername();
            Section section = sectionService.getSectionById(book.getSection().getId());

            book.setSection(section);
            Book savedBook = bookRespository.save(book);

            String bookId = BookIdGenerator.generateBookId(libraryUsername, book.getId(),
                    section.getName());

                    savedBook.setBookId(bookId);
                    return bookRespository.save(savedBook);
        }else{
            throw new IllegalStateException("Library Configuration not found");
        }

    }

    @Override
    public List<Book> getAllBooks() {
        return bookRespository.findAll();
    }

    @Override
    public Book getBookById(long id) {
        return bookRespository.findBookById(id);
    }

    @Override
    public Book updateBook(Book book, long id) {
        Book existingBook = bookRespository.findBookById(id);
        existingBook.setTitle(book.getTitle());
        existingBook.setAuthor(book.getAuthor());
        existingBook.setClassification(book.getClassification());
        existingBook.setCoverImageUrl(book.getCoverImageUrl());
        existingBook.setEditionNumber(book.getEditionNumber());
        existingBook.setIsbn(book.getIsbn());
        existingBook.setNumberOfPages(book.getNumberOfPages());
        existingBook.setPrice(book.getPrice());
        existingBook.setPublicationDate(book.getPublicationDate());
        existingBook.setPublisher(book.getPublisher());
        existingBook.setSection(book.getSection());
        existingBook.setNumberOfPages(book.getNumberOfPages());
        return bookRespository.save(existingBook);
    }

    @Override
    public boolean deleteBook(long id) {
        bookRespository.deleteById(id);
        return false;
    }

    @Override
    public Book getBookByTitle(String title) {
       Book book = bookRespository.findByTitle(title);
       if(book == null){
           throw new IllegalArgumentException("Book not found");
       }
       return book;   
    
    }

    @Override
    public Book getBookByBookId(String bookId) {
      Book book = bookRespository.findByBookId(bookId);
        if(book == null){
            throw new IllegalArgumentException("Book not found");
        }
        return book;
    }

}
