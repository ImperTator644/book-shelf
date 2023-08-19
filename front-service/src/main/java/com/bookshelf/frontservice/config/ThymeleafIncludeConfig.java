package com.bookshelf.frontservice.config;

import com.bookshelf.frontservice.service.CurrentUserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("thymeleaf")
public class ThymeleafIncludeConfig {

    @Value("${log.current.empty.user}")
    private String emptyUser;
    private final CurrentUserService currentUserService;

    public ThymeleafIncludeConfig(CurrentUserService currentUserService) {
        this.currentUserService = currentUserService;
    }

    public String getCurrentUserPesel(){
        return currentUserService.getCurrentUser();
    }
    public String getEmptyUser(){
        return emptyUser;
    }
}
