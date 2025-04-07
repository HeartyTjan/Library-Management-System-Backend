package com.swifthearty.libraryms.services;

import com.swifthearty.libraryms.dto.request.CreateNewUserRequest;
import com.swifthearty.libraryms.dto.request.UserLoginRequest;
import com.swifthearty.libraryms.dto.response.CreateNewUserResponse;
import com.swifthearty.libraryms.dto.response.UserLoginResponse;

public interface AdminService{
    CreateNewUserResponse createUser(CreateNewUserRequest newUser);

    UserLoginResponse login(UserLoginRequest loginRequest);
}
