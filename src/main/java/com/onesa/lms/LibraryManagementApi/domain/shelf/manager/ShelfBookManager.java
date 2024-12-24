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
