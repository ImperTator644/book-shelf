package com.luximed.gateway.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.validation.constraints.*;
import java.util.Collection;

@Data
@Table("personal_data")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomUserDetails implements UserDetails {

    @Id
    @Column("pesel")
    @JsonProperty("pesel")
    @NotNull(message = "Pesel nie został prawidłowo podany")
    @NotBlank(message = "Pesel nie został prawidłowo podany")
    @Pattern(regexp = "^[0-9]{11}", message = "Pesel musi mieć 11 cyfr")
    private String username;
    @NotNull(message = "Hasło nie zostało podane poprawnie")
    @NotBlank(message = "Hasło nie zostało podane poprawnie")
    @Size(min = 5, message = "Hasło jest za krótkie (min 5 znaków)")
    private String password;
    @NotNull(message = "Imię nie zostało podane poprawnie")
    @NotBlank(message = "Imię nie zostało podane poprawnie")
    private String name;
    @NotNull(message = "Nazwisko nie zostało podane poprawnie")
    @NotBlank(message = "Nazwisko nie zostało podane poprawnie")
    private String surname;
    @NotNull(message = "E-mail nie został podany poprawnie")
    @NotBlank(message = "E-mail nie został podany poprawnie")
    @Email(message = "E-mail nie został podany poprawnie")
    private String mail;
    @NotNull(message = "Telefon nie został podany poprawnie")
    @NotBlank(message = "Telefon nie został podany poprawnie")
    @Pattern(regexp = "[0-9]{9}", message = "Telefon powinien zawierać 9 cyfr (numer kierunkowy nie jest wymagany)")
    private String phone;
    private Gender gender;

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
