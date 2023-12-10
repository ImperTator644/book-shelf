package com.bookshelf.frontservice.dto;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class UserDTO {
    private String username;
    private String password;
}
