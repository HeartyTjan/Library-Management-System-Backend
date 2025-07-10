package com.swifthearty.libraryms.controller;

import com.swifthearty.libraryms.data.repository.TransactionRepository;
import com.swifthearty.libraryms.dto.request.AddTransactionRequest;
import com.swifthearty.libraryms.dto.request.UpdateTransactionRequest;
import com.swifthearty.libraryms.dto.response.GeneralResponse;
import com.swifthearty.libraryms.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transaction")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @PostMapping("/add")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    private ResponseEntity<GeneralResponse> addTransaction(@RequestBody AddTransactionRequest addTransactionRequest ) {
        return new ResponseEntity<>(transactionService.addTransaction(addTransactionRequest), HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    private ResponseEntity<GeneralResponse> removeTransaction(@RequestBody String transactionId) {
        return new ResponseEntity<>(transactionService.removeTransaction(transactionId), HttpStatus.OK);
    }

    @PutMapping("/update")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    private ResponseEntity<GeneralResponse> updateTransaction(@RequestBody UpdateTransactionRequest updateTransactionRequest ) {
        return new ResponseEntity<>(transactionService.updateTransaction(updateTransactionRequest), HttpStatus.OK);
    }
}
