package com.bookshelf.frontservice.service;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CurrentUserService {
    @Value("${log.user.auth.token}")
    private String loggedUserToken;

    @Value("${log.gateway.endpoint}")
    private String gatewayEndpoint;

    @Value("${log.gateway.endpoint.name}")
    private String gatewayEndpointName;

    @Value("${log.current.empty.user}")
    @Getter
    private String emptyUser;

    private final RestTemplate restTemplate;

    public CurrentUserService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String getCurrentUser() {
        return restTemplate.getForObject(String.format(gatewayEndpoint, loggedUserToken), String.class);
    }

    public void setLoggedUserName(final String newName) {
        restTemplate.postForEntity(String.format(gatewayEndpointName, loggedUserToken), newName, String.class);
    }
}
