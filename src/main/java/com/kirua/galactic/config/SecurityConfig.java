package com.kirua.galactic.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {
    private final PasswordEncoder passwordEncoder;

    @Bean
    public UserDetailsService userDetailsService() {
        final UserDetails userLambda = User.withUsername("plop-man")
                .password(passwordEncoder.encode("root"))
                .roles("USER")
                .build();

        final UserDetails admin = User.withUsername("chef-plop")
                .password(passwordEncoder.encode("root"))
                .roles("ADMIN")
                .build();

        return new InMemoryUserDetailsManager(
                userLambda,
                admin
        );
    }
}
