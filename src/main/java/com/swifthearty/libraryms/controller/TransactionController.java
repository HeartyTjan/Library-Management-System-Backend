package com.swifthearty.libraryms.controller;

import com.swifthearty.libraryms.data.repository.TransactionRepository;
import com.swifthearty.libraryms.dto.request.AddTransactionRequest;
import com.swifthearty.libraryms.dto.request.UpdateTransactionRequest;
import com.swifthearty.libraryms.dto.response.GeneralResponse;
import com.swifthearty.libraryms.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transaction")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @PostMapping("/add")
    private ResponseEntity<GeneralResponse> addTransaction(AddTransactionRequest addTransactionRequest ) {
        return new ResponseEntity<>(transactionService.addTransaction(addTransactionRequest), HttpStatus.OK);
    }


    @DeleteMapping("/delete")
    private ResponseEntity<GeneralResponse> removeTransaction(String transactionId) {
        return new ResponseEntity<>(transactionService.removeTransaction(transactionId), HttpStatus.OK);
    }

    @PutMapping("/update")
    private ResponseEntity<GeneralResponse> updateTransaction(UpdateTransactionRequest updateTransactionRequest ) {
        return new ResponseEntity<>(transactionService.updateTransaction(updateTransactionRequest), HttpStatus.OK);
    }
}
