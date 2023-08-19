package com.bookshelf.gateway.config;

import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class CurrentUser {
    @Value("${log.current.empty.user}")
    private String emptyUser;

    private String userName;

    @PostConstruct
    public void init() {
        this.userName = this.emptyUser;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
