package com.swifthearty.libraryms.services;

import com.swifthearty.libraryms.data.model.Book;
import com.swifthearty.libraryms.dto.request.AddBookDetailsRequest;
import com.swifthearty.libraryms.dto.request.ChangePasswordRequest;
import com.swifthearty.libraryms.dto.response.GeneralResponse;

import java.util.List;

public interface BookService {
    GeneralResponse addBook(AddBookDetailsRequest request);

    GeneralResponse changeStatusToBorrowed(String bookId);

    GeneralResponse changeStatusToReturned(String bookId);

    GeneralResponse removeBook(String bookId);

    List<Book> retrieveAllBooks();

    Book findBookById(String bookId);

}
