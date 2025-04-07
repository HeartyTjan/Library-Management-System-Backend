package com.swifthearty.libraryms.utility;

import lombok.Getter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Getter
public class SecuredDetails {

    public static final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public static String hashPassword(String Password){
        return passwordEncoder.encode(Password);
    }

    public static boolean matchPassword(String Password, String hashedPassword){
        return passwordEncoder.matches(Password, hashedPassword);
    }
    public static String hashPhoneNumber(String phoneNumber){
        return passwordEncoder.encode(phoneNumber);
    }
}
