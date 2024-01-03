package com.bookshelf.gateway.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.relational.core.mapping.Column;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDetailsDto {

    @NotNull(message = "You need to specify 'username'")
    @NotBlank(message = "You need to specify 'username'")
    @Column
    @JsonProperty("username")
    private String username;

    @NotNull(message = "You need to specify 'password'")
    @NotBlank(message = "You need to specify 'password'")
    @Size(min = 5, message = "Password to short (at least 5 characters required)")
    @JsonProperty("password")
    private String password;

    private String repeatPassword;
}
