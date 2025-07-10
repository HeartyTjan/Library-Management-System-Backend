package com.swifthearty.libraryms.services;

import com.swifthearty.libraryms.data.model.Admin;
import com.swifthearty.libraryms.dto.request.ChangePasswordRequest;
import com.swifthearty.libraryms.dto.request.CreateNewUserRequest;
import com.swifthearty.libraryms.dto.request.UserLoginRequest;
import com.swifthearty.libraryms.dto.response.CreateNewUserResponse;
import com.swifthearty.libraryms.dto.response.UserLoginResponse;

public interface AdminService{

    Admin createNewAdmin();

    UserLoginResponse login(UserLoginRequest loginRequest);

    boolean changePassword(ChangePasswordRequest request);
}
