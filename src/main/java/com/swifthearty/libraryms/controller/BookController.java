package com.swifthearty.libraryms.controller;

import com.swifthearty.libraryms.data.model.Book;
import com.swifthearty.libraryms.dto.request.AddBookDetailsRequest;
import com.swifthearty.libraryms.dto.response.GeneralResponse;
import com.swifthearty.libraryms.services.BookService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    private BookService bookService;


    @PostMapping("/addBook")
    private ResponseEntity<GeneralResponse> addBook(@Valid  @RequestBody AddBookDetailsRequest addBookDetailsRequest) {
        return new ResponseEntity<>(bookService.addBook(addBookDetailsRequest), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    private ResponseEntity<GeneralResponse> removeBook(@PathVariable("id") String bookId) {
        return new ResponseEntity<>(bookService.removeBook(bookId), HttpStatus.OK);
    }

    @PutMapping("/borrow/{id}")
    private ResponseEntity<GeneralResponse> statusToBorrowed(@PathVariable("id") String bookId) {
        return new ResponseEntity<>(bookService.changeStatusToBorrowed(bookId), HttpStatus.OK);
    }
    @PutMapping("/return/{id}")
    private ResponseEntity<GeneralResponse> statusToReturned(@PathVariable("id") String bookId) {
        return new ResponseEntity<>(bookService.changeStatusToReturned(bookId), HttpStatus.OK);
    }

    @GetMapping
    private ResponseEntity<List<Book>> getAllBooks() {
        return new ResponseEntity<>(bookService.retrieveAllBooks(), HttpStatus.OK);
    }









}
