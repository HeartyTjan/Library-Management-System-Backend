package com.swifthearty.libraryms.data.repository;

import com.swifthearty.libraryms.data.model.UserVerificationToken;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserVerificationTokenRepository extends MongoRepository<UserVerificationToken, String> {
    UserVerificationToken findByToken(String token);
}
