package com.swifthearty.libraryms.data.model;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document
@Data
@RequiredArgsConstructor
public class UserVerificationToken {


    private String id;
    @NonNull
    private String token;
    @NonNull
    private Member member;
    @NonNull
    private LocalDateTime expiryTime;
}
