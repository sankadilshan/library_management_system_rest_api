package com.libraryManagement.service;

import com.libraryManagement.model.Book;
import com.libraryManagement.model.DTO.BookDto;
import com.libraryManagement.model.DTO.Result;

import java.util.List;

public interface LibraryService {
    boolean initiate(Book book);
    BookDto findById(long id);
    Result findByAuthor(String author,int pageNumber,String sort);
    Result findByTitle(String title);
    Result findAll(int pageNumber, String sort);

}
