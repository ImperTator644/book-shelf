package com.luximed.gateway.client;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@RequiredArgsConstructor
@Service
public class FrontClient {
    private static final String FRONT_URI = "http://localhost:8181/auth";
    private final RestTemplate template;

    public void logUser(String pesel){
        template.postForEntity(FRONT_URI + "/log", pesel, String.class);
    }
}
