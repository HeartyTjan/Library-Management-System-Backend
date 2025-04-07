package com.swifthearty.libraryms.services;

import com.swifthearty.libraryms.data.model.Book;
import com.swifthearty.libraryms.dto.request.CreateNewUserRequest;
import com.swifthearty.libraryms.dto.request.UserLoginRequest;
import com.swifthearty.libraryms.dto.response.CreateNewUserResponse;
import com.swifthearty.libraryms.dto.response.GeneralResponse;
import com.swifthearty.libraryms.dto.response.UserLoginResponse;
import org.springframework.stereotype.Service;

@Service
public interface MemberServices {
    CreateNewUserResponse createUser(CreateNewUserRequest newUser);

    UserLoginResponse login(UserLoginRequest loginRequest);

    GeneralResponse addBooKToCart(String bookId, String memberId);

    GeneralResponse removeBooKFromCart(String bookId, String memberId);
}
