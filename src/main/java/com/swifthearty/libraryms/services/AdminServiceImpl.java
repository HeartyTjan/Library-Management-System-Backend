package com.swifthearty.libraryms.services;

import com.swifthearty.libraryms.data.model.Admin;
import com.swifthearty.libraryms.data.repository.AdminRepository;
import com.swifthearty.libraryms.dto.request.CreateNewUserRequest;
import com.swifthearty.libraryms.dto.request.UserLoginRequest;
import com.swifthearty.libraryms.dto.response.CreateNewUserResponse;
import com.swifthearty.libraryms.dto.response.UserLoginResponse;
import com.swifthearty.libraryms.utility.SecuredDetails;
import com.swifthearty.libraryms.utility.exceptions.ResourcesAlreadyExistException;
import com.swifthearty.libraryms.utility.exceptions.ResourcesNotFoundException;
import com.swifthearty.libraryms.utility.mapper.CreateUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements  AdminService {

    @Autowired
    private AdminRepository adminRepository;


    @Override
    public CreateNewUserResponse createUser(CreateNewUserRequest newUser) {

        if(adminRepository.existsByEmail(newUser.getEmail())) {
            throw new ResourcesAlreadyExistException("User already exist");
        }
        Admin admin = CreateUserMapper.mapToAdmin(newUser);
        adminRepository.save(admin);
        return CreateUserMapper.mapToResponse("User created successfully",true);
    }

    @Override
    public UserLoginResponse login(UserLoginRequest loginRequest){
        Admin admin = adminRepository.findByEmail(loginRequest.getEmail())
                .orElseThrow(()-> new ResourcesNotFoundException("Invalid Email or Password"));

        boolean isLoginSuccessfully = SecuredDetails.matchPassword(loginRequest.getPassword(), admin.getPassword());
        if(isLoginSuccessfully){

            return CreateUserMapper.mapToLoginResponse("Login Successfully",true);
        }
        return CreateUserMapper.mapToLoginResponse("Login Failed",false);
    }

}
