package com.bookshelf.gateway.client;

import com.bookshelf.gateway.model.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@RequiredArgsConstructor
@Service
public class DBClient {
    private static final String DATABASE_URI = "http://localhost:8083/api/database";
    private final RestTemplate template;

    public ResponseEntity<String> saveUser(CustomUserDetails userData) {
        return template.postForEntity(DATABASE_URI + "/user/add",
                userData,
                String.class);
    }
}
