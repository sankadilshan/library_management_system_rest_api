package com.libraryManagement.controller;

import com.libraryManagement.model.Book;
import com.libraryManagement.model.DTO.BookDto;
import com.libraryManagement.model.DTO.Result;
import com.libraryManagement.service.LibraryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class Controller {

    private static final Logger logger = LoggerFactory.getLogger(Controller.class);
    @Autowired
    private LibraryService libraryService;

    @GetMapping("/health")
    public String health() {
        return "OK";
    }

    @GetMapping
    public ResponseEntity<?> retrieve(@RequestParam(defaultValue = "0") int pageNumber, @RequestParam(defaultValue = "title") String sort) throws RuntimeException{
        logger.info("start fetching");
        Result books = libraryService.findAll(pageNumber,sort);
        return books==null ? new ResponseEntity<>("no records",HttpStatus.OK): new ResponseEntity<>(books,HttpStatus.OK);
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> initiateBook(@RequestBody Book book) {
        logger.info("start initiate book ");
        boolean initiate = libraryService.initiate(book);
        if (initiate)
            return new ResponseEntity<>("created successfully", HttpStatus.CREATED);
        return new ResponseEntity<>("bad saving", HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/authors/{author}/books")
    public ResponseEntity<?> retrieveBooks(@PathVariable("author") String author, @RequestParam(defaultValue = "0") int pageNumber, @RequestParam(defaultValue = "author") String sort) {
        logger.info("start retrieve");
        Result books = libraryService.findByAuthor(author,pageNumber,sort);
        return books==null ? new ResponseEntity<>("no records", HttpStatus.NOT_FOUND) : new ResponseEntity<>(books, HttpStatus.OK);
    }
}

