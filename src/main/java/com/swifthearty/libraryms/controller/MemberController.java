package com.swifthearty.libraryms.controller;

import com.swifthearty.libraryms.data.repository.MemberRepository;
import com.swifthearty.libraryms.dto.request.AddBookDetailsRequest;
import com.swifthearty.libraryms.dto.request.AddBookToCartRequest;
import com.swifthearty.libraryms.dto.request.RemoveBookFromCartRequest;
import com.swifthearty.libraryms.dto.response.GeneralResponse;
import com.swifthearty.libraryms.services.MemberServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/member")
public class MemberController {

    @Autowired
    private MemberServices memberServices;

    @PostMapping("/addToCart")
    @PreAuthorize("hasAnyAuthority('ROLE_MEMBER')")
    public ResponseEntity<GeneralResponse> addBookToCart(@RequestBody AddBookToCartRequest request){
        GeneralResponse response = memberServices.addBooKToCart(request.getBookId(),request.getMemberId());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/removeBookFromCart")
    @PreAuthorize("hasAnyAuthority('ROLE_MEMBER')")
    public ResponseEntity<GeneralResponse> removeBookFromCart(@RequestBody RemoveBookFromCartRequest request){
        GeneralResponse response = memberServices.removeBooKFromCart(request.getBookId(),request.getMemberId());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
