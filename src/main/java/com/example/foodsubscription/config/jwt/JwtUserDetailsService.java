package com.example.foodsubscription.config.jwt;

import com.example.foodsubscription.domain.entity.User;
import com.example.foodsubscription.repo.UserRepositoryService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class JwtUserDetailsService implements UserDetailsService {
    private UserRepositoryService userRepo;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        User user = userRepo.findByLogin(login);
        return new JwtUserDetails(user);
    }
}
