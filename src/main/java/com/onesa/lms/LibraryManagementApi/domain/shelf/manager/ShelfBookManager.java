package com.onesa.lms.LibraryManagementApi.domain.shelf.manager;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onesa.lms.LibraryManagementApi.domain.book.management.model.Book;
import com.onesa.lms.LibraryManagementApi.domain.book.management.repository.BookRespository;
import com.onesa.lms.LibraryManagementApi.domain.shelf.model.Shelf;
import com.onesa.lms.LibraryManagementApi.domain.shelf.model.ShelfBook;
import com.onesa.lms.LibraryManagementApi.domain.shelf.repository.ShelfBookRepository;
import com.onesa.lms.LibraryManagementApi.domain.shelf.repository.ShelfRepository;
import com.onesa.lms.LibraryManagementApi.domain.shelf.service.ShelfBookService;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ShelfBookManager implements ShelfBookService {

    @Autowired
    private ShelfBookRepository shelfBookRepository;
    
    @Autowired
    private BookRespository bookRepository;

    @Autowired
    private ShelfRepository shelfRepository;

    @Override
    public ShelfBook createShelfBook(ShelfBook shelfBook) {

       
        List<ShelfBook> existingShelfBooks = shelfBookRepository.findByBookId(shelfBook.getBook().getId());
        if (!existingShelfBooks.isEmpty()) {
            throw new IllegalStateException("Book with id " + shelfBook.getBook().getId() + " is already on another shelf.");
        }
    
        Book book = bookRepository.findBookById(shelfBook.getBook().getId());
        if (book == null) {
            throw new EntityNotFoundException("Book with id " + shelfBook.getBook().getId() + " not found.");
        }
    
        Shelf shelf = shelfRepository.findShelfById(shelfBook.getShelf().getId());
        if (shelf == null) {
            throw new EntityNotFoundException("Shelf with id " + shelfBook.getShelf().getId() + " not found.");
        }
    
        shelfBook.setBook(book);
        shelfBook.setShelf(shelf);
        return shelfBookRepository.save(shelfBook);
    }

    @Override
    public List<ShelfBook> getAllShelfBooks() {

        return shelfBookRepository.findAll();
    }

    @Override
    public ShelfBook getShelfBookById(long id) {

        return shelfBookRepository.findShelfBookById(id);
    }

    @Override
    public ShelfBook updateShelfBook(long id, long bookId,  long shelfId) {
        ShelfBook existingShelfBook = shelfBookRepository.findShelfBookById(id);
        if (existingShelfBook == null) {
            throw new EntityNotFoundException("ShelfBook with id " + id + " not found.");
        }

        Book book = bookRepository.findBookById(bookId);
        Shelf shelf = shelfRepository.findShelfById(shelfId);

        existingShelfBook.setBook(book);
        existingShelfBook.setShelf(shelf);

        return shelfBookRepository.save(existingShelfBook);
    }

    @Override
    public boolean deleteShelfBook(long id) {
        shelfBookRepository.deleteById(id);
        return false;
    }

}
