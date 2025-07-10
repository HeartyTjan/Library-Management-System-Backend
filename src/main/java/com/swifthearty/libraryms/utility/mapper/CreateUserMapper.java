package com.swifthearty.libraryms.utility.mapper;

import com.swifthearty.libraryms.data.model.Admin;
import com.swifthearty.libraryms.data.model.Member;
import com.swifthearty.libraryms.dto.request.CreateNewUserRequest;
import com.swifthearty.libraryms.dto.response.CreateNewUserResponse;
import com.swifthearty.libraryms.dto.response.GeneralResponse;
import com.swifthearty.libraryms.dto.response.UserLoginResponse;
import com.swifthearty.libraryms.utility.SecuredDetails;
import org.apache.commons.lang3.RandomStringUtils;

import java.security.SecureRandom;

public class CreateUserMapper{

    public static Member mapToUMember(CreateNewUserRequest createNewUserRequest) {
        Member member = new Member();
        member.setFirstName(createNewUserRequest.getFirstName());
        member.setLastName(createNewUserRequest.getLastName());
        member.setEmail(createNewUserRequest.getEmail());
        member.setUsername(createNewUserRequest.getUsername());
        member.setPhoneNumber(createNewUserRequest.getPhoneNumber());
        String hashedPassword =  SecuredDetails.hashPassword(createNewUserRequest.getPassword());
        member.setPassword(hashedPassword);
        member.setRole(createNewUserRequest.getRole());

        return member;
    }

    public static Admin mapToAdmin() {
        Admin newAdmin = new Admin();
        SecureRandom random = new SecureRandom();
        String admin = "admin" + String.valueOf(random.nextInt(10,50)) + "@gmail.com";
        newAdmin.setEmail(admin);
        String password = RandomStringUtils.randomAlphanumeric(10);
        newAdmin.setPassword(password);
        newAdmin.setRole("ROLE_ADMIN");

        return newAdmin;
    }

    public static CreateNewUserResponse mapToResponse(String message, boolean success) {
        CreateNewUserResponse response = new CreateNewUserResponse();
        response.setMessage(message);
        response.setSuccess(success);
        return response;
    }
    public static UserLoginResponse mapToLoginResponse(String message, boolean success,String token) {
        UserLoginResponse loginResponse = new UserLoginResponse();
        loginResponse.setMessage(message);
        loginResponse.setSuccess(success);
        loginResponse.setToken(token);
        return loginResponse;
    }

    public static GeneralResponse mapToGeneralResponse(String message, String token) {
        GeneralResponse response = new GeneralResponse();
        response.setMessage(message);
        response.setToken(token);
        return response;
    }

}