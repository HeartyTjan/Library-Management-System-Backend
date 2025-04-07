package com.swifthearty.libraryms.utility.mapper;

import com.swifthearty.libraryms.data.model.Book;
import com.swifthearty.libraryms.dto.request.AddBookDetailsRequest;
import com.swifthearty.libraryms.dto.response.GeneralResponse;

public class BookMapper {

    public static Book mapToBook(AddBookDetailsRequest request){
        Book book = new Book();
        book.setAuthor(request.getAuthor());
        book.setTitle(request.getTitle());
        book.setQuantity(request.getQuantity());
        book.setAvailable(request.isAvailable());
        return  book;
    }

    public static GeneralResponse mapBookToResponse(String message, boolean success) {
        GeneralResponse response = new GeneralResponse();
        response.setMessage(message);
        response.setSuccess(success);
        return response;
    }
}
