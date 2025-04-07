package com.swifthearty.libraryms.services;

import com.swifthearty.libraryms.data.repository.AdminRepository;
import com.swifthearty.libraryms.dto.request.CreateNewUserRequest;
import com.swifthearty.libraryms.dto.request.UserLoginRequest;
import com.swifthearty.libraryms.dto.response.CreateNewUserResponse;
import com.swifthearty.libraryms.dto.response.UserLoginResponse;
import com.swifthearty.libraryms.utility.exceptions.ResourcesAlreadyExistException;
import com.swifthearty.libraryms.utility.exceptions.ResourcesNotFoundException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class AdminServiceImplTest {

    @Autowired
    private AdminService adminService;

    @Autowired
    private AdminRepository adminRepository;

    CreateNewUserRequest newAdmin = new CreateNewUserRequest();

    @BeforeEach
    public void setUp() {
        adminRepository.deleteAll();

        newAdmin.setFirstName("Admin");
        newAdmin.setLastName("User");
        newAdmin.setEmail("admin@domain.com");
        newAdmin.setPassword("adminPassword");
    }

    @Test
    public void adminCanBeCreated_countIsOneTest() {
        CreateNewUserResponse response = adminService.createUser(newAdmin);
        assertEquals("User created successfully", response.getMessage());
        assertEquals(1, adminRepository.count());
    }

    @Test
    public void testThatAdminCannotRegisterTwice_throwsException() {
        CreateNewUserResponse response = adminService.createUser(newAdmin);
        assertEquals("User created successfully", response.getMessage());
        assertEquals(1, adminRepository.count());

        assertThrows(ResourcesAlreadyExistException.class, () -> adminService.createUser(newAdmin),"User already exist");
    }

    @Test
    public void adminCanLoginWithCorrectDetails_returnsTrue() {
        CreateNewUserResponse response = adminService.createUser(newAdmin);
        assertEquals("User created successfully", response.getMessage());
        assertEquals(1, adminRepository.count());

        UserLoginRequest loginRequest = new UserLoginRequest();
        loginRequest.setEmail("admin@domain.com");
        loginRequest.setPassword("adminPassword");

        UserLoginResponse loginResponse = adminService.login(loginRequest);
        assertTrue(loginResponse.isSuccess());
    }

    @Test
    public void adminCannotLoginWithIncorrectDetails_returnsFalse() {
        CreateNewUserResponse response = adminService.createUser(newAdmin);
        assertEquals("User created successfully", response.getMessage());
        assertEquals(1, adminRepository.count());

        UserLoginRequest loginRequest = new UserLoginRequest();
        loginRequest.setEmail("admin@domain.com");
        loginRequest.setPassword("incorrectPassword");

        UserLoginResponse loginResponse = adminService.login(loginRequest);
        assertFalse(loginResponse.isSuccess());
        assertEquals("Login Failed", loginResponse.getMessage());
    }

    @Test
    public void adminCannotLoginWithNonExistentEmail_throwsException() {
        UserLoginRequest loginRequest = new UserLoginRequest();
        loginRequest.setEmail("nonexistent@domain.com");
        loginRequest.setPassword("password");

        assertThrows(ResourcesNotFoundException.class, () -> adminService.login(loginRequest));
    }
}