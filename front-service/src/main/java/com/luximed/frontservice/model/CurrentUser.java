package com.luximed.frontservice.model;

import com.luximed.frontservice.util.UserUtils;
import org.springframework.stereotype.Component;

@Component
public class CurrentUser {
    private String userName = UserUtils.EMPTY_USER;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}