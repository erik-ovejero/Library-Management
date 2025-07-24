package com.domain.Vo;

import com.domain.Book;
import com.domain.User;
import lombok.Data;

@Data
public class BorrowingBooksVo {
    private User user;
    private Book book;  // Borrow book
    private String dateOfBorrowing;  // Borrow date
    private String dateOfReturn;    // Return date
}
