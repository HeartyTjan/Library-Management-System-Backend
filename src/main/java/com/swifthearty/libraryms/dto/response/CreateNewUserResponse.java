package com.swifthearty.libraryms.dto.response;

import lombok.Data;

@Data
public class CreateNewUserResponse {
    private String message;
    private boolean success;
    private String token;
}

