package com.bookshelf.frontservice.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CurrentUserService {
    @Value("${log.user.auth.token}")
    private String loggedUserToken;

    @Value("${log.gateway.endpoint}")
    private String gatewayEndpoint;

    private final RestTemplate restTemplate;

    public CurrentUserService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String getCurrentUser() {
        return restTemplate.getForObject(String.format(gatewayEndpoint, loggedUserToken), String.class);
    }
}
