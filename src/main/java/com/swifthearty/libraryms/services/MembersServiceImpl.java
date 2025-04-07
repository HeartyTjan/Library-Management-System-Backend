package com.swifthearty.libraryms.services;

import com.swifthearty.libraryms.data.model.Book;
import com.swifthearty.libraryms.data.model.Member;
import com.swifthearty.libraryms.data.repository.MemberRepository;
import com.swifthearty.libraryms.dto.request.CreateNewUserRequest;
import com.swifthearty.libraryms.dto.request.UserLoginRequest;
import com.swifthearty.libraryms.dto.response.CreateNewUserResponse;
import com.swifthearty.libraryms.dto.response.GeneralResponse;
import com.swifthearty.libraryms.dto.response.UserLoginResponse;
import com.swifthearty.libraryms.utility.SecuredDetails;
import com.swifthearty.libraryms.utility.exceptions.ResourcesAlreadyExistException;
import com.swifthearty.libraryms.utility.exceptions.ResourcesNotFoundException;
import com.swifthearty.libraryms.utility.mapper.BookMapper;
import com.swifthearty.libraryms.utility.mapper.CreateUserMapper;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MembersServiceImpl implements MemberServices{

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private BookService bookService;
    @Getter
    private final List<Book> cart = new ArrayList<Book>();

    @Override
    public CreateNewUserResponse createUser(CreateNewUserRequest newUser) {

        if(memberRepository.existsByEmail(newUser.getEmail())) {
            throw new ResourcesAlreadyExistException("User already exist");
        }
        Member member = CreateUserMapper.mapToUMember(newUser);
        memberRepository.save(member);
        return CreateUserMapper.mapToResponse("User created successfully",true);
    }

    @Override
    public UserLoginResponse login(UserLoginRequest loginRequest){
        Member member = memberRepository.findByEmail(loginRequest.getEmail())
                .orElseThrow(()-> new ResourcesNotFoundException("Invalid Email or Password"));

        boolean isLoginSuccessfully = SecuredDetails.matchPassword(loginRequest.getPassword(), member.getPassword());
        if(isLoginSuccessfully){

            return CreateUserMapper.mapToLoginResponse("Login Successfully",true);
        }
        return CreateUserMapper.mapToLoginResponse("Login Failed",false);
    }

    @Override
    public GeneralResponse addBooKToCart(String bookId, String memberId){
        Member member = memberRepository.findById(memberId).orElseThrow(()-> new ResourcesNotFoundException("Member not found"));
        Book bookToBeAddedToCart = bookService.findBookById(bookId);
        member.getCart().add(bookToBeAddedToCart);
        return BookMapper.mapBookToResponse("Book is added", true);
    }

    @Override
    public GeneralResponse removeBooKFromCart(String bookId, String memberId){
        Member member = memberRepository.findById(memberId).orElseThrow(()-> new ResourcesNotFoundException("Member not found"));
        Book bookToBeAddedToCart = bookService.findBookById(bookId);
        member.getCart().remove(bookToBeAddedToCart);
        return BookMapper.mapBookToResponse("Book removed", true);
    }
}
