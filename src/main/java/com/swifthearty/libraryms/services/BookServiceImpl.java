package com.swifthearty.libraryms.services;

import com.swifthearty.libraryms.data.model.Book;
import com.swifthearty.libraryms.data.model.Member;
import com.swifthearty.libraryms.data.repository.BookRepository;
import com.swifthearty.libraryms.data.repository.MemberRepository;
import com.swifthearty.libraryms.dto.request.AddBookDetailsRequest;
import com.swifthearty.libraryms.dto.request.ChangePasswordRequest;
import com.swifthearty.libraryms.dto.response.GeneralResponse;
import com.swifthearty.libraryms.utility.SecuredDetails;
import com.swifthearty.libraryms.utility.exceptions.ResourcesAlreadyExistException;
import com.swifthearty.libraryms.utility.exceptions.ResourcesNotFoundException;
import com.swifthearty.libraryms.utility.mapper.BookMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {


    private final BookRepository bookRepository;

    @Override
    @CacheEvict(cacheNames = "books", key = "'all'")
    public GeneralResponse addBook(AddBookDetailsRequest request) {
        if(bookRepository.existsBookByTitleAndAuthor(request.getTitle(), request.getAuthor())){
            throw new ResourcesAlreadyExistException("Book already added to catalogue");
        }
        Book book = BookMapper.mapToBook(request);
        bookRepository.save(book);
        return BookMapper.mapBookToResponse("Book Added", true);
    }

    @Override
    @CachePut(cacheNames = "books", key = "#bookId")
    public GeneralResponse changeStatusToBorrowed(String bookId){
        Book bookToBeChangedToBorrowed = bookRepository.findById(bookId).orElseThrow(()-> new ResourcesNotFoundException("Book not found"));
        bookToBeChangedToBorrowed.setBorrowed(true);
        bookRepository.save(bookToBeChangedToBorrowed);
        return BookMapper.mapBookToResponse("Book Borrowed", true);
    }

    @Override
    @CachePut(cacheNames = "books", key = "#bookId")
    public GeneralResponse changeStatusToReturned(String bookId){
        Book bookToReturned =  bookRepository.findById(bookId).orElseThrow(()-> new ResourcesNotFoundException("Book not found"));
        bookToReturned.setReturned(true);
        bookRepository.save(bookToReturned);
        return BookMapper.mapBookToResponse("Book Returned", true);
    }

    @Override
    @CacheEvict(cacheNames = "books", key = "#bookId")
    public GeneralResponse removeBook(String bookId){
        bookRepository.deleteById(bookId);
        return BookMapper.mapBookToResponse("Book Deleted", true);
    }

    @Override
    @Cacheable(cacheNames = "books", key = "'all'")
    public List<Book> retrieveAllBooks(){
        log.info("fetching from database");
        return bookRepository.findAll();
    }

    @Override
    @Cacheable(cacheNames = "books", key = "#bookId")
    public Book findBookById(String bookId){
        log.info("finding from database");
        return bookRepository.findById(bookId).orElseThrow(()-> new ResourcesNotFoundException("Book not found"));
    }

}
