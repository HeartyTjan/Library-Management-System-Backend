package com.swifthearty.libraryms.data.model;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document
@Data
public class Member {
    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private List<Book> cart = new ArrayList<>();
    private String password;
    private String role;
}
