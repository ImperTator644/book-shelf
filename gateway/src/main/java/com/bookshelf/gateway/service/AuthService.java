package com.bookshelf.gateway.service;

import com.bookshelf.gateway.client.DBClient;
import com.bookshelf.gateway.model.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final DBClient dbClient;
    private final PasswordEncoder passwordEncoder;

    @Value("${log.user.auth.token}")
    private String loggedUserToken;

    public ResponseEntity<String> saveUser(CustomUserDetails userData) {
        userData.setPassword(passwordEncoder.encode(userData.getPassword()));
        return dbClient.saveUser(userData);
    }

    public boolean validateLoggedUserToken(String token) {
        return loggedUserToken.equals(token);
    }
}
