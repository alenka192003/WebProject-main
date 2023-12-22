package com.example.webwork.services.Impl;

import com.example.webwork.models.Users;
import com.example.webwork.repo.UsersRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class AppUserDetailsService implements UserDetailsService {
    private UsersRepository userRepository;
    public AppUserDetailsService(UsersRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        return userRepository.findByUserName(userName)
                .map(u -> {
                    List<GrantedAuthority> authorities = Collections.singletonList(
                            new SimpleGrantedAuthority("ROLE_" + u.getRole().getRoleEnum().name())
                    );
                    return new User(
                            u.getUserName(),
                            u.getPassword(),
                            authorities
                    );
                })
                .orElseThrow(() -> new UsernameNotFoundException(userName + " was not found!"));
    }
}




