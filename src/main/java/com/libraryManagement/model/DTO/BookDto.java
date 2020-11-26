package com.libraryManagement.model.DTO;

import lombok.Data;

@Data
public class BookDto {
    private String title;
    private String author;
    private int page;
    private String price;

}
