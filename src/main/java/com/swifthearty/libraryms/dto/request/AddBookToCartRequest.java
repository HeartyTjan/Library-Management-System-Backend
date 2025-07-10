package com.swifthearty.libraryms.dto.request;

import lombok.Data;

@Data
public class AddBookToCartRequest {

    private String bookId;
    private String memberId;

}
