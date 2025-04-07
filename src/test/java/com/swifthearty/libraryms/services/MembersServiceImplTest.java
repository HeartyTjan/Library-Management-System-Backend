package com.swifthearty.libraryms.services;

import com.swifthearty.libraryms.data.repository.MemberRepository;
import com.swifthearty.libraryms.dto.request.CreateNewUserRequest;
import com.swifthearty.libraryms.dto.request.UserLoginRequest;
import com.swifthearty.libraryms.dto.response.CreateNewUserResponse;
import com.swifthearty.libraryms.dto.response.UserLoginResponse;
import com.swifthearty.libraryms.utility.exceptions.ResourcesAlreadyExistException;
import com.swifthearty.libraryms.utility.exceptions.ResourcesNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MembersServiceImplTest {

    @Autowired
    private MemberServices memberServices;
    
    @Autowired
    private MemberRepository memberRepository;

    CreateNewUserRequest newMember = new CreateNewUserRequest();

    @BeforeEach
    void setUp() {
        memberRepository.deleteAll();
        newMember.setFirstName("John");
        newMember.setLastName("Doe");
        newMember.setEmail("john.doe@example.com");
        newMember.setPassword("password");
    }
    
    @Test
    public void memberCanBeCreated_countIsOneTest() {
        CreateNewUserResponse response = memberServices.createUser(newMember);
        assertEquals("User created successfully", response.getMessage());
        assertEquals(1, memberRepository.count());
    }

    @Test
    public void testThatAdminCannotRegisterTwice_throwsException() {
        CreateNewUserResponse response = memberServices.createUser(newMember);
        assertEquals("User created successfully", response.getMessage());
        assertEquals(1, memberRepository.count());

        assertThrows(ResourcesAlreadyExistException.class, () -> memberServices.createUser(newMember),"User already exist");
    }

    @Test
    public void memberCanLoginWithCorrectDetails_returnsTrue() {
        CreateNewUserResponse response = memberServices.createUser(newMember);
        assertEquals("User created successfully", response.getMessage());
        assertEquals(1, memberRepository.count());

        UserLoginRequest loginRequest = new UserLoginRequest();
        loginRequest.setEmail("john.doe@example.com");
        loginRequest.setPassword("password");

        UserLoginResponse loginResponse = memberServices.login(loginRequest);
        assertTrue(loginResponse.isSuccess());
    }

    @Test
    public void memberCannotLoginWithIncorrectDetails_returnsFalse() {
        CreateNewUserResponse response = memberServices.createUser(newMember);
        assertEquals("User created successfully", response.getMessage());
        assertEquals(1, memberRepository.count());

        UserLoginRequest loginRequest = new UserLoginRequest();
        loginRequest.setEmail("john.doe@example.com");
        loginRequest.setPassword("incorrectPassword");

        UserLoginResponse loginResponse = memberServices.login(loginRequest);
        assertFalse(loginResponse.isSuccess());
        assertEquals("Login Failed", loginResponse.getMessage());
    }

    @Test
    public void memberCannotLoginWithNonExistentEmail_throwsException() {
        UserLoginRequest loginRequest = new UserLoginRequest();
        loginRequest.setEmail("nonexistent@domain.com");
        loginRequest.setPassword("password");

        assertThrows(ResourcesNotFoundException.class, () -> memberServices.login(loginRequest));
    }

}