package com.domain.Vo;

import lombok.Data;

@Data
public class BookVo {
    private Integer bookId;  

    private String bookName; 

    private String bookAuthor;

    private String bookPublish;

    private String isExist;  // Whether a book is available for borrowing

}
