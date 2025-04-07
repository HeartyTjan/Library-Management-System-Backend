package com.swifthearty.libraryms.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AddTransactionRequest {
    @Valid

    @NotNull(message = "Book Id cannot be null")
    @NotBlank(message = "Book Id is required")
    private String bookId;


    @NotNull(message = "Member Id cannot be null")
    @NotBlank(message = "Member Id is required")
    private String memberId;

    @NotNull(message = "Borrow Date cannot be null")
    @NotBlank(message = "Borrow Date is required")
    private LocalDateTime borrowDate;

    @NotNull(message = "Return Date cannot be null")
    @NotBlank(message = "Return Date is required")
    private LocalDateTime returnDate;

    @NotNull(message = "Due Date Date cannot be null")
    @NotBlank(message = "Due Date is required")
    private LocalDateTime dueDate;
}

