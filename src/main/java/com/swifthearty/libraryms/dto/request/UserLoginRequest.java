package com.swifthearty.libraryms.dto.request;

import lombok.Data;

@Data
public class UserLoginRequest {
    private String email;
    private String password;
    private String role;
}
