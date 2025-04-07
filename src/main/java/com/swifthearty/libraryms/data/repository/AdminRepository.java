package com.swifthearty.libraryms.data.repository;

import com.swifthearty.libraryms.data.model.Admin;
import jakarta.validation.constraints.Email;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface AdminRepository extends MongoRepository <Admin, String>{

    Optional<Admin> findByEmail(String email);

    boolean existsByEmail( String email);
}
