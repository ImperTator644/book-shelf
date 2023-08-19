package com.bookshelf.gateway.service;

import com.bookshelf.gateway.model.CustomUserDetails;
import com.bookshelf.gateway.client.DBClient;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final DBClient dbClient;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @Value("${log.user.auth.token}")
    private String loggedUserToken;

    public void savePatient(CustomUserDetails personalData) {
        personalData.setPassword(passwordEncoder.encode(personalData.getPassword()));
        dbClient.savePersonalData(personalData);
        dbClient.savePatient(personalData.getUsername());
    }

    public String generateToken(String pesel) {
        return jwtService.generateToken(pesel);
    }

    public void validateToken(String token) {
        jwtService.validateToken(token);
    }

    public boolean validateLoggedUserToken(String token) {
        return loggedUserToken.equals(token);
    }
}
