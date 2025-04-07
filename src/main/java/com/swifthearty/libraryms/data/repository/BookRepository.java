package com.swifthearty.libraryms.data.repository;

import com.swifthearty.libraryms.data.model.Book;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BookRepository extends MongoRepository <Book, String>{
    boolean existsBookByTitleAndAuthor(String title, String author);
}