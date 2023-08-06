package com.luximed.gateway.service;

import com.luximed.gateway.client.DBClient;
import com.luximed.gateway.model.CustomUserDetails;
import com.luximed.gateway.repository.PersonalInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final DBClient dbClient;
    private final PersonalInfoRepository personalInfoRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public void savePatient(CustomUserDetails personalData) {
        personalData.setPassword(passwordEncoder.encode(personalData.getPassword()));
        dbClient.savePersonalData(personalData);
        dbClient.savePatient(personalData.getUsername());
    }

    public String generateToken(String pesel){
        return jwtService.generateToken(pesel);
    }

    public void validateToken(String token){
        jwtService.validateToken(token);
    }
}
