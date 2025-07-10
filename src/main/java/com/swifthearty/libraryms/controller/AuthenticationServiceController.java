package com.swifthearty.libraryms.controller;

import com.swifthearty.libraryms.dto.request.ChangePasswordRequest;
import com.swifthearty.libraryms.dto.request.CreateNewUserRequest;
import com.swifthearty.libraryms.dto.request.UserLoginRequest;
import com.swifthearty.libraryms.services.AdminService;
import com.swifthearty.libraryms.services.MemberServices;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AuthenticationServiceController {

    private final AdminService adminService;
    private final MemberServices memberServices;

    @PostMapping("/member")
    private ResponseEntity<?> createNewUser(@Valid @RequestBody CreateNewUserRequest request){
        return ResponseEntity.ok(memberServices.createUser(request));
    }
    @PostMapping("/admin")
    private ResponseEntity<?> createNewAdmin(){
        return ResponseEntity.ok(adminService.createNewAdmin());
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserLoginRequest request) {

        switch (request.getRole().toLowerCase()){
            case "role_member" -> {
                return ResponseEntity.ok(memberServices.loginUser(request));
            }
            case "role_admin" -> {
                return ResponseEntity.ok(adminService.login(request));
            }
            default -> {
                return  ResponseEntity.badRequest().body("Invalid  Role");
            }
        }
    }

    @PutMapping("/admin/changePassword")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    public ResponseEntity<Boolean> changePassword(@RequestBody ChangePasswordRequest request) {
        Boolean isChanged = adminService.changePassword(request);
        return new ResponseEntity<>(isChanged, HttpStatus.OK);
    }

    @PutMapping("/member/changePassword")
    @PreAuthorize("hasAnyAuthority('ROLE_MEMBER')")
    public ResponseEntity<Boolean> changePasswordMember(@RequestBody ChangePasswordRequest request) {
        Boolean isChanged = memberServices.changePassword(request);
        return new ResponseEntity<>(isChanged, HttpStatus.OK);
    }
}
