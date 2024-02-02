package com.example.foodsubscription.service;

import com.example.foodsubscription.config.jwt.JwtUtil;
import com.example.foodsubscription.domain.dto.AuthorizationDTO;
import com.example.foodsubscription.domain.entity.User;
import com.example.foodsubscription.repo.UserRepositoryService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class AuthService {
    private AuthenticationManager authenticationManager;
    private UserDetailsService userDetailsService;
    private JwtUtil jwtTokenUtil;
    private UserRepositoryService tokenUserRepositoryService;

    public ResponseEntity<?> authenticateUser(AuthorizationDTO.Request.AuthRequest authRequest) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatusCode.valueOf(401)).body("Invalid credentials");
        }
        UserDetails userDetails = userDetailsService
                .loadUserByUsername(authRequest.getUsername());
        final String jwt = jwtTokenUtil.generateToken(userDetails);

        return ResponseEntity.ok(new AuthorizationDTO.Response.AuthResponse(jwt));
    }

    public ResponseEntity<?> saveUser(AuthorizationDTO.Request.AddNewUserRequest user){
        try {
            tokenUserRepositoryService.save(new User(user));
        } catch (Exception ex) {
            log.error("User can't be added!");
            return ResponseEntity.status(HttpStatusCode.valueOf(500)).body("User already exist");
        }
        return ResponseEntity.ok("User was added!");
    }
}
