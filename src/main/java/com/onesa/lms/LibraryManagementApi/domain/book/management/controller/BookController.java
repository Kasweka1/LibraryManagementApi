package com.onesa.lms.LibraryManagementApi.domain.book.management.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.onesa.lms.LibraryManagementApi.core.utils.dtos.ApiResponse;
import com.onesa.lms.LibraryManagementApi.domain.book.management.model.Book;
import com.onesa.lms.LibraryManagementApi.domain.book.management.service.BookService;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    public BookService bookService;

    @PostMapping
    public ResponseEntity<ApiResponse<Book>> createBook(@RequestBody Book book) {
        try {
            Book createdBook = bookService.createBook(book);
            ApiResponse<Book> response = new ApiResponse<>(1200, "Book created successfully", createdBook);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            System.out.println(e);
            ApiResponse<Book> response = new ApiResponse<>(1500, "Failed to create book", null);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<Book>>> getAllBooks() {
        try {
            List<Book> books = bookService.getAllBooks();

            if (books.isEmpty()) {
                ApiResponse<List<Book>> response = new ApiResponse<>(1404, "No Books found", books);
                return new ResponseEntity<>(response, HttpStatus.OK);
            }

            ApiResponse<List<Book>> response = new ApiResponse<>(1103, "Books retrieved successfully", books);
            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (Exception e) {
            ApiResponse<List<Book>> response = new ApiResponse<>(1500, "Failed to retrieve Books", null);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Book>> getBook(@PathVariable long id) {

        try {
            Book book = bookService.getBookById(id);
            if (book == null) {
                ApiResponse<Book> response = new ApiResponse<>(1105, "Book not found", null);
                return new ResponseEntity<>(response, HttpStatus.OK);
            }

            ApiResponse<Book> response = new ApiResponse<>(1104, "Book found", book);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            ApiResponse<Book> response = new ApiResponse<>(1120, "An error occurred", null);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Book>> updateBook(@PathVariable long id,
            @RequestBody Book updatedBook) {
        try {
            Book book = bookService.updateBook(updatedBook, id);

            if (book == null) {
                ApiResponse<Book> response = new ApiResponse<>(1105, "Book not found", null);
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }

            ApiResponse<Book> response = new ApiResponse<>(1107, "Book updated successfully", book);
            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (Exception e) {
            ApiResponse<Book> response = new ApiResponse<>(1120, "Failed to update Book", null);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteBook(@PathVariable long id) {
        try {
            boolean deleted = bookService.deleteBook(id);

            if (!deleted) {
                ApiResponse<Void> response = new ApiResponse<>(1001, "Book not found", null);
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }

            ApiResponse<Void> response = new ApiResponse<>(1000, "Book deleted successfully", null);
            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (Exception e) {
            ApiResponse<Void> response = new ApiResponse<>(1120, "Failed to delete Book", null);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
