package com.swifthearty.libraryms.utility.mapper;

import com.swifthearty.libraryms.data.model.Admin;
import com.swifthearty.libraryms.data.model.Member;
import com.swifthearty.libraryms.dto.request.CreateNewUserRequest;
import com.swifthearty.libraryms.dto.response.CreateNewUserResponse;
import com.swifthearty.libraryms.dto.response.UserLoginResponse;
import com.swifthearty.libraryms.utility.SecuredDetails;

public class CreateUserMapper{

    public static Member mapToUMember(CreateNewUserRequest createNewUserRequest) {
        Member member = new Member();
        member.setFirstName(createNewUserRequest.getFirstName());
        member.setLastName(createNewUserRequest.getLastName());
        member.setEmail(createNewUserRequest.getEmail());
        member.setPhoneNumber(createNewUserRequest.getPhoneNumber());
        String hashedPassword =  SecuredDetails.hashPassword(createNewUserRequest.getPassword());
        member.setPassword(hashedPassword);
        member.setRole(createNewUserRequest.getRole());

        return member;
    }

    public static Admin mapToAdmin(CreateNewUserRequest createNewUserRequest) {
        Admin admin = new Admin();
        admin.setFirstName(createNewUserRequest.getFirstName());
        admin.setLastName(createNewUserRequest.getLastName());
        admin.setEmail(createNewUserRequest.getEmail());
        admin.setPhoneNumber(createNewUserRequest.getPhoneNumber());
        String hashedPassword =  SecuredDetails.hashPassword(createNewUserRequest.getPassword());
        admin.setPassword(hashedPassword);
        admin.setRole(createNewUserRequest.getRole());

        return admin;
    }

    public static CreateNewUserResponse mapToResponse(String message, boolean success) {
        CreateNewUserResponse response = new CreateNewUserResponse();
        response.setMessage(message);
        response.setSuccess(success);
        return response;
    }
    public static UserLoginResponse mapToLoginResponse(String message, boolean success){
        UserLoginResponse loginResponse = new UserLoginResponse();
        loginResponse.setMessage(message);
        loginResponse.setSuccess(success);
        return loginResponse;
    }

}