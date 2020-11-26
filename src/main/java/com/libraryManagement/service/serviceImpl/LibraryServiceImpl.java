package com.libraryManagement.service.serviceImpl;

import com.libraryManagement.model.Book;
import com.libraryManagement.model.DTO.BookDto;
import com.libraryManagement.model.DTO.Result;
import com.libraryManagement.repository.BookRepository;
import com.libraryManagement.service.LibraryService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LibraryServiceImpl implements LibraryService {

    private static final Logger logger = LoggerFactory.getLogger(LibraryServiceImpl.class);

    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Transactional
    @Override
    public boolean initiate(Book book) {
        try {
            Book savedBook = bookRepository.save(book);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Result findAll(int pageNumber, String s) {
        try {

            Sort sort = Sort.by(s).ascending();
            Pageable pageable = PageRequest.of(pageNumber, 10, sort);
            Page<Book> book = bookRepository.findAll(pageable);
            return convertion(book, pageNumber);

        } catch (NullPointerException e) {

            logger.error(e.toString());
            throw new NullPointerException();
        }
    }

    @Override
    public BookDto findById(long id) {

        Optional<Book> book = bookRepository.findById((int) id);
        return book.isPresent() ? modelMapper.map(book, BookDto.class) : null;
    }

    @Override
    public Result findByAuthor(String author, int pageNumber, String sort) {
        try {

            Pageable pageable = PageRequest.of(pageNumber, 10, Sort.by(sort).descending());
            Page<Book> book = bookRepository.findByAuthor(author, pageable);
            return convertion(book, pageNumber);

        } catch (NullPointerException e) {

            logger.error(e.toString());
            throw new NullPointerException();
        }
    }

    @Override
    public Result findByTitle(String title) {
        return null;
    }


    private Result convertion(Page<Book> book, int pageNumber) {

        List<Book> books = book.getContent();
        List<BookDto> bookDto = books.stream().map(b -> modelMapper.map(b, BookDto.class)).collect(Collectors.toList());
        return new Result(book.getTotalElements(), book.getTotalPages(), 10, pageNumber, bookDto);

    }

}
