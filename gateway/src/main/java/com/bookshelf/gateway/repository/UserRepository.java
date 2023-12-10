package com.bookshelf.gateway.repository;

import com.bookshelf.gateway.model.CustomUserDetails;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.security.core.userdetails.UserDetails;
import reactor.core.publisher.Mono;

public interface UserRepository extends ReactiveCrudRepository<CustomUserDetails, String> {
    Mono<UserDetails> findByUsername(String username);
}
