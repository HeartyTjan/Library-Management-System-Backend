package com.swifthearty.libraryms.controller;

import com.swifthearty.libraryms.dto.request.CreateNewUserRequest;
import com.swifthearty.libraryms.dto.request.UserLoginRequest;
import com.swifthearty.libraryms.services.AdminService;
import com.swifthearty.libraryms.services.MemberServices;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class AuthenticationService {

    @Autowired
    private AdminService adminService;

    @Autowired
    private MemberServices memberServices;

    @PostMapping("/register")
    private ResponseEntity<?> createNewUser(@Valid @RequestBody CreateNewUserRequest request){
        switch (request.getRole().toLowerCase()){
            case "member" -> {
                return ResponseEntity.ok(memberServices.createUser(request));
            }
            case "admin" -> {
                return ResponseEntity.ok(adminService.createUser(request));
            }
            default -> {
                return  ResponseEntity.badRequest().body("Invalid  Role");
            }
        }

    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserLoginRequest request) {

        switch (request.getRole().toLowerCase()){
            case "member" -> {
                return ResponseEntity.ok(memberServices.login(request));
            }
            case "admin" -> {
                return ResponseEntity.ok(adminService.login(request));
            }
            default -> {
                return  ResponseEntity.badRequest().body("Invalid  Role");
            }
        }
    }

}
