package com.swifthearty.libraryms.data.repository;

import com.swifthearty.libraryms.data.model.Transaction;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TransactionRepository extends MongoRepository <Transaction, String>{
}
