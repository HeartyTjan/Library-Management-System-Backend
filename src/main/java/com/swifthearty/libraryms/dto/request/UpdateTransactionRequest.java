package com.swifthearty.libraryms.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UpdateTransactionRequest {
    @NotNull(message = "Transaction Id cannot be null")
    @NotBlank(message = "Transaction Id is required")
    private String transactionId;

    @NotNull(message = "Return Date cannot be null")
    @NotBlank(message = "Return Date is required")
    private LocalDateTime returnDate;

}
