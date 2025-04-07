package com.swifthearty.libraryms.services;

import com.swifthearty.libraryms.data.model.Transaction;
import com.swifthearty.libraryms.data.repository.TransactionRepository;
import com.swifthearty.libraryms.dto.request.AddTransactionRequest;
import com.swifthearty.libraryms.dto.request.UpdateTransactionRequest;
import com.swifthearty.libraryms.dto.response.GeneralResponse;
import com.swifthearty.libraryms.utility.mapper.TransactionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;


    @Override
    public GeneralResponse addTransaction(AddTransactionRequest addTransactionRequest) {
        Transaction transaction = TransactionMapper.mapToTransaction(addTransactionRequest);
        transactionRepository.save(transaction);
        return TransactionMapper.mapTransactionToResponse("New transaction added", true);
    }
    @Override
    public GeneralResponse removeTransaction(String transactionId) {
        transactionRepository.deleteById(transactionId);
        return TransactionMapper.mapTransactionToResponse("Transaction removed", true);
    }

    @Override
    public GeneralResponse updateTransaction(UpdateTransactionRequest updateTransactionRequest) {
        transactionRepository.findById(updateTransactionRequest.getTransactionId()).ifPresent(transaction -> {
            Transaction updatedTransaction = TransactionMapper.mapUpdateToTransaction(transaction, updateTransactionRequest);
            transactionRepository.save(updatedTransaction);
        });
        return TransactionMapper.mapTransactionToResponse("Transaction updated", true);
    }
}
