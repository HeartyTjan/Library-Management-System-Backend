package com.swifthearty.libraryms.dto.request;

import lombok.Data;

@Data
public class RemoveBookFromCartRequest {

    private String bookId;
    private String memberId;
}
