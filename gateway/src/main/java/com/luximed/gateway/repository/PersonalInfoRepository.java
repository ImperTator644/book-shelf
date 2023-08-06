package com.luximed.gateway.repository;

import com.luximed.gateway.model.CustomUserDetails;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.security.core.userdetails.UserDetails;
import reactor.core.publisher.Mono;

public interface PersonalInfoRepository extends ReactiveCrudRepository<CustomUserDetails, String> {
    Mono<UserDetails> findByUsername(String pesel);
}
