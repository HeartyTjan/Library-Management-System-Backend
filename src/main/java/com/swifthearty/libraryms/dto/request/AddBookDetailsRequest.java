package com.swifthearty.libraryms.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
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

    @NotNull(message = "isbn cannot be null")
    @NotBlank(message = "isbn is required")
    private String isbn;

    @NotNull(message = "Type cannot be null")
    @NotBlank(message = "Type is required")
    private String type;

    @NotNull(message = "Language cannot be null")
    @NotBlank(message = "Language is required")
    private String language;

    private boolean available;

    @NotNull(message = "quantity cannot be null")
    @Positive
    @Min(0)
    private int quantity;

}