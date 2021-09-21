package com.kirua.galactic.config;

import com.kirua.galactic.security.JwtAuthFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import org.springframework.security.web.header.writers.ReferrerPolicyHeaderWriter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    private final JwtAuthFilter jwtAuthFilter;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                .and().httpBasic()
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET, "/picture").permitAll()
                .antMatchers("/picture/find").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE,"/picture").hasRole("ADMIN")
                .antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/picture/admin/reset").hasRole("ADMIN")
                .antMatchers("/open/picture/**").permitAll()
                .and().csrf().disable()
                .formLogin();

                http.headers()
                        .xssProtection()
                        .and()
                        .contentSecurityPolicy("script-src 'self'")
                        .and().permissionsPolicy(permissions -> permissions
                                .policy("geolocation 'self'")
                        )
                        .and().referrerPolicy(referrerPolicy ->
                                referrerPolicy
                                        .policy(ReferrerPolicyHeaderWriter.ReferrerPolicy.NO_REFERRER_WHEN_DOWNGRADE)
                        );

                http.authorizeRequests()
                        .antMatchers("/user/sign-up", "/auth").permitAll()
                        .anyRequest().authenticated();

                http.addFilterBefore(
                        jwtAuthFilter,
                        UsernamePasswordAuthenticationFilter.class
                );

                http.cors();
    }

    @Override @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
