package com.swifthearty.libraryms.utility.mapper;

import com.swifthearty.libraryms.data.model.Transaction;
import com.swifthearty.libraryms.dto.request.AddTransactionRequest;
import com.swifthearty.libraryms.dto.request.UpdateTransactionRequest;
import com.swifthearty.libraryms.dto.response.GeneralResponse;

public class TransactionMapper {

    public static Transaction mapToTransaction(AddTransactionRequest request) {
        Transaction transaction = new Transaction();
        transaction.setBookId(request.getBookId());
        transaction.setMemberId(request.getMemberId());
        transaction.setBorrowDate(request.getBorrowDate());
        transaction.setReturnDate(request.getReturnDate());
        transaction.setDueDate(request.getDueDate());
        return transaction;
    }

    public static GeneralResponse mapTransactionToResponse(String message, boolean success) {
        return BookMapper.mapBookToResponse(message, success);
    }


    public static Transaction mapUpdateToTransaction(Transaction transactionToBeUpdated, UpdateTransactionRequest request){

        transactionToBeUpdated.setBookId(request.getBookId());
        transactionToBeUpdated.setMemberId(request.getMemberId());
        transactionToBeUpdated.setReturnDate(request.getReturnDate());
        transactionToBeUpdated.setBorrowDate(request.getBorrowDate());
        transactionToBeUpdated.setDueDate(request.getDueDate());
        return transactionToBeUpdated;
    }
}
