package com.onesa.lms.LibraryManagementApi.domain.book.circulation.controller.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LendBookRequest {
    private Long bookId;
    private Long memberId;
}
