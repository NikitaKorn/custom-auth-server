package com.example.foodsubscription.config.jwt;

import com.example.foodsubscription.domain.entity.TokenUser;
import com.example.foodsubscription.repo.TokenUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JwtUserDetailsService implements UserDetailsService {
    @Autowired
    private TokenUserRepository dao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        TokenUser user = dao.findByLogin(username);
        return User.builder()
                .username(user.getLogin())
                .password(user.getPassword())
                .roles(user.getRole())
                .build();
    }
}
