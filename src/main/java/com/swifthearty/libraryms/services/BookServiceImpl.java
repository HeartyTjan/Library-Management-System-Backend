package com.swifthearty.libraryms.services;

import com.swifthearty.libraryms.data.model.Book;
import com.swifthearty.libraryms.data.repository.BookRepository;
import com.swifthearty.libraryms.dto.request.AddBookDetailsRequest;
import com.swifthearty.libraryms.dto.response.GeneralResponse;
import com.swifthearty.libraryms.utility.exceptions.ResourcesAlreadyExistException;
import com.swifthearty.libraryms.utility.exceptions.ResourcesNotFoundException;
import com.swifthearty.libraryms.utility.mapper.BookMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    @Override
    public GeneralResponse addBook(AddBookDetailsRequest request) {
        if(bookRepository.existsBookByTitleAndAuthor(request.getTitle(), request.getAuthor())){
            throw new ResourcesAlreadyExistException("Book already added to catalogue");
        }
        Book book = BookMapper.mapToBook(request);
        bookRepository.save(book);
        return BookMapper.mapBookToResponse("Book Added", true);
    }

    @Override
    public GeneralResponse changeStatusToBorrowed(String bookId){
        Book bookToBeChangedToBorrowed = bookRepository.findById(bookId).orElseThrow(()-> new ResourcesNotFoundException("Book not found"));
        bookToBeChangedToBorrowed.setBorrowed(true);
        bookRepository.save(bookToBeChangedToBorrowed);
        return BookMapper.mapBookToResponse("Book Borrowed", true);
    }

    @Override
    public GeneralResponse changeStatusToReturned(String bookId){
        Book bookToReturned = findBookById(bookId);
        bookToReturned.setReturned(true);
        bookRepository.save(bookToReturned);
        return BookMapper.mapBookToResponse("Book Returned", true);
    }

    @Override
    public GeneralResponse removeBook(String bookId){
        bookRepository.deleteById(bookId);
        return BookMapper.mapBookToResponse("Book Deleted", true);
    }

    @Override
    public List<Book> retrieveAllBooks(){
        return bookRepository.findAll();
    }

    @Override
    public Book findBookById(String bookId){
        return bookRepository.findById(bookId).orElseThrow(()-> new ResourcesNotFoundException("Book not found"));
    }


}
