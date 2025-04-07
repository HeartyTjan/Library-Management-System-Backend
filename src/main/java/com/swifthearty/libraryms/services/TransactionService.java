package com.swifthearty.libraryms.services;

import com.swifthearty.libraryms.dto.request.AddTransactionRequest;
import com.swifthearty.libraryms.dto.request.UpdateTransactionRequest;
import com.swifthearty.libraryms.dto.response.GeneralResponse;

public interface TransactionService {
    GeneralResponse addTransaction(AddTransactionRequest addTransactionRequest);

    GeneralResponse removeTransaction(String transactionId);

    GeneralResponse updateTransaction(UpdateTransactionRequest updateTransactionRequest);
}
