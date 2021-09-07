package com.kirua.galactic.security;

//import com.kirua.galactic.domain.user.User;
import com.kirua.galactic.repository.UserRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        com.kirua.galactic.domain.user.User user = this.userRepository.findPersonByLogin(login)
                .orElseThrow(() -> new UsernameNotFoundException(login));

        UserDetails userDetails = User.builder()
                .username(user.getLogin())
                .password(user.getPassword())
                .roles(user.getRole())
                .build();

        return userDetails;
    }
}
