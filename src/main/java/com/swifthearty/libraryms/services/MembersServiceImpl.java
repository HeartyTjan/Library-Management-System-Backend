package com.swifthearty.libraryms.services;

import com.swifthearty.libraryms.data.model.Book;
import com.swifthearty.libraryms.data.model.Member;
import com.swifthearty.libraryms.data.model.UserVerificationToken;
import com.swifthearty.libraryms.data.repository.MemberRepository;
import com.swifthearty.libraryms.data.repository.UserVerificationTokenRepository;
import com.swifthearty.libraryms.dto.request.ChangePasswordRequest;
import com.swifthearty.libraryms.dto.request.CreateNewUserRequest;
import com.swifthearty.libraryms.dto.request.UserLoginRequest;
import com.swifthearty.libraryms.dto.response.CreateNewUserResponse;
import com.swifthearty.libraryms.dto.response.GeneralResponse;
import com.swifthearty.libraryms.dto.response.UserLoginResponse;
import com.swifthearty.libraryms.utility.SecuredDetails;
import com.swifthearty.libraryms.utility.exceptions.InvalidTokenException;
import com.swifthearty.libraryms.utility.exceptions.ResourcesAlreadyExistException;
import com.swifthearty.libraryms.utility.exceptions.ResourcesNotFoundException;
import com.swifthearty.libraryms.utility.mapper.BookMapper;
import com.swifthearty.libraryms.utility.mapper.CreateUserMapper;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class MembersServiceImpl implements MemberServices{


    private final MemberRepository memberRepository;
    private final BookService bookService;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final JavaMailSenderImpl mailSender;

    @Getter
    private final List<Book> cart = new ArrayList<Book>();
    private final UserVerificationTokenRepository userVerificationTokenRepository;

    @Override
    public CreateNewUserResponse createUser(CreateNewUserRequest newUser) {

        if(memberRepository.existsByEmail(newUser.getEmail())) {
            throw new ResourcesAlreadyExistException("User already exist");
        }
        Member member = CreateUserMapper.mapToUMember(newUser);
        memberRepository.save(member);
        createVerificationAndSendToken(member);

        return CreateUserMapper.mapToResponse("Check your email to verify account",true);
    }

    @Override
    public UserLoginResponse loginUser(UserLoginRequest loginRequest) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

        var member = memberRepository.findByEmail(loginRequest.getEmail())
                .orElseThrow(()-> new ResourcesNotFoundException("User not found"));

        var jwtToken = jwtService.generateToken(member);
        return CreateUserMapper.mapToLoginResponse("Login Successfully", true, jwtToken);
    }

    @Override
    public GeneralResponse addBooKToCart(String bookId, String memberId){
        Member member = memberRepository.findById(memberId)
                .orElseThrow(()-> new ResourcesNotFoundException("Member not found"));

        Book bookToBeAddedToCart = bookService.findBookById(bookId);

        if (!member.getCart().contains(bookToBeAddedToCart)) {
            member.getCart().add(bookToBeAddedToCart);
        }
        return BookMapper.mapBookToResponse("Book is added", true);
    }

    @Override
    public GeneralResponse removeBooKFromCart(String bookId, String memberId){
        Member member = memberRepository.findById(memberId)
                .orElseThrow(()-> new ResourcesNotFoundException("Member not found"));

        Book bookToRemoveFromCart = bookService.findBookById(bookId);
        member.getCart().remove(bookToRemoveFromCart);
        return BookMapper.mapBookToResponse("Book removed", true);
    }

    @Override
    public boolean changePassword(ChangePasswordRequest request) {
        Member foundMember =  memberRepository.findById(request.getId()).orElseThrow(()-> new ResourcesNotFoundException("Member not found"));
        if(!SecuredDetails.matchPassword(foundMember.getPassword(),request.getOldPassword())) {
            return false;
        }
        foundMember.setPassword(SecuredDetails.hashPassword(request.getNewPassword()));
        memberRepository.save(foundMember);
        return true;
    }

    private void createVerificationAndSendToken(Member member){
        String token = UUID.randomUUID().toString();
        LocalDateTime expiryDate = LocalDateTime.now().plusMinutes(5);

        UserVerificationToken verificationToken = new UserVerificationToken(token,member,expiryDate);
        userVerificationTokenRepository.save(verificationToken);
        sendVerificationEmail(member.getEmail(), token);
    }

    private void sendVerificationEmail(String email, String token) {
        String url = "http://localhost:8080/member/verify" + "?token=" + token;

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("Verify your email");
        message.setText("Click the link to verify your account: " + url);

        mailSender.send(message);
    }

    @Override
    public GeneralResponse verifyPatientAccount(String token) {
        UserVerificationToken verificationToken = userVerificationTokenRepository.findByToken(token);

        if (verificationToken == null) {
            throw new InvalidTokenException("Invalid verification token.");
        }

        if (verificationToken.getExpiryTime().isBefore(LocalDateTime.now())) {
            throw new InvalidTokenException("Token has expired.");
        }

       Member member = verificationToken.getMember();
        member.setVerified(true);
        memberRepository.save(member);
        userVerificationTokenRepository.delete(verificationToken);

        var jwtToken = jwtService.generateToken(member);

        return CreateUserMapper.mapToGeneralResponse("Account verified successfully!", jwtToken);
    }



}
