package com.onesa.lms.LibraryManagementApi.domain.book.circulation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.onesa.lms.LibraryManagementApi.core.utils.dtos.ApiResponse;
import com.onesa.lms.LibraryManagementApi.domain.book.circulation.controller.utils.LendBookRequest;
import com.onesa.lms.LibraryManagementApi.domain.book.circulation.model.BookCirculation;
import com.onesa.lms.LibraryManagementApi.domain.book.circulation.service.BookCirculationService;

@RestController
@RequestMapping("/book-circulation")
public class BookCirculationController {

  @Autowired
  private BookCirculationService bookCirculationService;

  @PostMapping("/lend-book")
  public ResponseEntity<ApiResponse<BookCirculation>> lendBook(@RequestBody LendBookRequest lendBookRequest) {
    try {
      BookCirculation bookCirculation = bookCirculationService.lendBook(
         lendBookRequest.getBookId(),
          lendBookRequest.getLibraryUserIdentider()
          );
      ApiResponse<BookCirculation> response = new ApiResponse<>(1200, "Book lent successfully", bookCirculation);
      return new ResponseEntity<>(response, HttpStatus.OK);
    } catch (Exception e) {
      ApiResponse<BookCirculation> response = new ApiResponse<>(1202, "Failed to lend book: " + e.getMessage(), null);
      return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
}
