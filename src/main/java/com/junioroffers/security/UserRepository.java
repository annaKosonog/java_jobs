package com.junioroffers.security;

import com.junioroffers.security.domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface UserRepository extends MongoRepository<User, String> {
     Optional <User> findByUsername(String username);
    boolean existsByUsername(String username);
}
