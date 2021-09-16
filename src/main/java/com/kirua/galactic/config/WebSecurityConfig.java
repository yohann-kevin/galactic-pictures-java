package com.kirua.galactic.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .httpBasic()
                .and()
                .authorizeRequests()
                .antMatchers("/user/sign-up").permitAll()
                .antMatchers(HttpMethod.GET, "/picture").permitAll()
                .antMatchers("/picture/find").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE,"/picture").hasRole("ADMIN")
                .antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/picture/admin/reset").hasRole("ADMIN")
//                .antMatchers("/user/session").permitAll()
                .anyRequest().authenticated()
//                .and().csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
                .and().csrf().disable()
                .formLogin()
                .defaultSuccessUrl("/user/session");
//                .defaultSuccessUrl("/user/current-user", true);
    }
}
