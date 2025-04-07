package com.swifthearty.libraryms.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AddBookDetailsRequest {
    @Valid
    @NotNull(message = "Book title cannot be null")
    @NotBlank(message = "Book title is required")
    private String title;

    @NotNull(message = "author cannot be null")
    @NotBlank(message = "author is required")
    private String author;

    @NotNull(message = "available cannot be null")
    @NotBlank(message = "available is required")
    private boolean available;

    @NotNull(message = "quantity cannot be null")
    @NotBlank(message = "quantity is required")
    private int quantity;
}