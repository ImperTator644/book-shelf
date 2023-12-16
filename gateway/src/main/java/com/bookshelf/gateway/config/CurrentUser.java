package com.bookshelf.gateway.config;

import javax.annotation.PostConstruct;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class CurrentUser {
    @Value("${log.current.empty.user}")
    private String emptyUser;

    @Getter
    @Setter
    private String userName;

    @PostConstruct
    public void init() {
        this.userName = this.emptyUser;
    }
}
