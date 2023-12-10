package com.bookshelf.gateway.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Collection;
import javax.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Data
@Table("user")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomUserDetails implements UserDetails {

    @Id
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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
