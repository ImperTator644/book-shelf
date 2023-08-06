package com.luximed.gateway.config;

import com.luximed.gateway.client.FrontClient;
import com.luximed.gateway.repository.PersonalInfoRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.WebFilterExchange;
import org.springframework.security.web.server.authentication.RedirectServerAuthenticationSuccessHandler;
import org.springframework.security.web.server.authentication.logout.RedirectServerLogoutSuccessHandler;
import reactor.core.publisher.Mono;

import java.net.URI;

@EnableWebFluxSecurity
@Configuration
public class AuthConfig {
    private static final String EMPTY_USER = "8g*C!F@%D!KYy9CPqY9Slvd80hnz97yGyT87_^*Pop0tD)5WcMGRSbViza&VVxn!O^Zz3r1tK!*cwDOBFPmx%%_2LT)i&F#deNkFY1uR1I0c(lSi@e*y*e#0Hg1k*fMw";
    private final PersonalInfoRepository personalInfoRepository;
    private final FrontClient frontClient;

    public AuthConfig(PersonalInfoRepository personalInfoRepository, FrontClient frontClient) {
        this.personalInfoRepository = personalInfoRepository;
        this.frontClient = frontClient;
    }

    @Bean
    public SecurityWebFilterChain securityFilterChain(ServerHttpSecurity http) {
        return http.csrf().disable()
                .authorizeExchange()
                .pathMatchers("/auth/register", "/register", "/", "/images/**", "/js/**", "/css/**").permitAll()
                .anyExchange().authenticated()
                .and()
                .formLogin()
                .authenticationSuccessHandler(new RedirectServerAuthenticationSuccessHandler("/") {
                    @Override
                    public Mono<Void> onAuthenticationSuccess(WebFilterExchange webFilterExchange, Authentication authentication) {
                        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
                        frontClient.logUser(userDetails.getUsername());
                        return super.onAuthenticationSuccess(webFilterExchange, authentication);
                    }
                })
                .and()
                .logout()
                .logoutSuccessHandler(new RedirectServerLogoutSuccessHandler() {
                    @Override
                    public Mono<Void> onLogoutSuccess(WebFilterExchange exchange, Authentication authentication) {
                        frontClient.logUser(EMPTY_USER);
                        this.setLogoutSuccessUrl(URI.create("/"));
                        return super.onLogoutSuccess(exchange, authentication);
                    }
                })
                .and()
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public ReactiveUserDetailsService userDetailsService() {
        return personalInfoRepository::findByUsername;
    }
}
