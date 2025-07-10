package com.swifthearty.libraryms.services;

import com.swifthearty.libraryms.data.model.Admin;
import com.swifthearty.libraryms.data.repository.AdminRepository;
import com.swifthearty.libraryms.dto.request.ChangePasswordRequest;
import com.swifthearty.libraryms.dto.request.UserLoginRequest;
import com.swifthearty.libraryms.dto.response.UserLoginResponse;
import com.swifthearty.libraryms.utility.SecuredDetails;
import com.swifthearty.libraryms.utility.exceptions.ResourcesNotFoundException;
import com.swifthearty.libraryms.utility.mapper.CreateUserMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements  AdminService {

    private final AdminRepository adminRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;


    @Override
    public Admin createNewAdmin() {
        Admin admin = CreateUserMapper.mapToAdmin();
        adminRepository.save(admin);
        return admin;
    }

    @Override
    public UserLoginResponse login(UserLoginRequest loginRequest) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
        var user = adminRepository.findByEmail(loginRequest.getEmail())
                .orElseThrow(()-> new ResourcesNotFoundException("User not found"));

        String token = jwtService.generateToken(user);
        return CreateUserMapper.mapToLoginResponse("Login Successfully", true,token);
    }

    @Override
    public boolean changePassword(ChangePasswordRequest request) {
        Admin foundAdmin =  adminRepository.findById(request.getId()).orElseThrow(()-> new ResourcesNotFoundException("Admin not found"));
        if (!foundAdmin.getPassword().equals(request.getOldPassword())) {
            return false;
        }
        String hashedNewPassword = SecuredDetails.hashPassword(request.getNewPassword());
        foundAdmin.setPassword(SecuredDetails.hashPassword(hashedNewPassword));
        adminRepository.save(foundAdmin);
        return true;
    }


}
