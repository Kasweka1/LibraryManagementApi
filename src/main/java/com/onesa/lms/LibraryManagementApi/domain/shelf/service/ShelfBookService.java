package com.onesa.lms.LibraryManagementApi.domain.shelf.service;

import java.util.List;

import com.onesa.lms.LibraryManagementApi.domain.shelf.model.ShelfBook;

public interface ShelfBookService {
    
    ShelfBook createShelfBook(ShelfBook shelfBook);

    List<ShelfBook> getAllShelfBooks();

    ShelfBook getShelfBookById(long id);

    ShelfBook updateShelfBook(long id, long bookId, long shelfId);

    boolean deleteShelfBook(long id);

}
