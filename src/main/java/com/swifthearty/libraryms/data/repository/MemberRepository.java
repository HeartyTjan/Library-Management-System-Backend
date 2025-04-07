package com.swifthearty.libraryms.data.repository;

import com.swifthearty.libraryms.data.model.Member;
import jakarta.validation.constraints.Email;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface MemberRepository extends MongoRepository <Member, String>{
    Optional<Member> findByEmail(String email);
    boolean existsByEmail(String email);
}
