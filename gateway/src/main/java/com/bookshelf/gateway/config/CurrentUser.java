package com.bookshelf.gateway.config;

import javax.annotation.PostConstruct;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class CurrentUser {
    @Value("${log.current.empty.user}")
    private String emptyUser;

    @Getter
    private String userName;

    @PostConstruct
    public void init() {
        this.userName = this.emptyUser;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
