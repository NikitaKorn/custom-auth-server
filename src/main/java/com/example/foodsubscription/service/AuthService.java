package com.example.foodsubscription.service;

import com.example.foodsubscription.config.jwt.JwtUserDetailsService;
import com.example.foodsubscription.config.jwt.JwtUtil;
import com.example.foodsubscription.domain.dto.AuthorizationDTO;
import com.example.foodsubscription.domain.entity.TokenUser;
import com.example.foodsubscription.repo.TokenUserRepositoryService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class AuthService {
    private AuthenticationManager authenticationManager;
    private JwtUserDetailsService jwtUserDetailsService;
    private JwtUtil jwtTokenUtil;
    private TokenUserRepositoryService tokenUserRepositoryService;

    public ResponseEntity<?> authenticateUser(AuthorizationDTO.Request.AuthRequest authRequest) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatusCode.valueOf(401)).body("Invalid credentials");
        }
        UserDetails userDetails = jwtUserDetailsService
                .loadUserByUsername(authRequest.getUsername());
        final String jwt = jwtTokenUtil.generateToken(userDetails);

        return ResponseEntity.ok(new AuthorizationDTO.Response.AuthResponse(jwt));
    }

    public ResponseEntity<?> saveUser(AuthorizationDTO.Request.AddNewUserRequest user){
        try {
            tokenUserRepositoryService.save(new TokenUser(user));
        } catch (Exception ex) {
            log.error("User can't be added!");
            return ResponseEntity.status(HttpStatusCode.valueOf(500)).body("User already exist");
        }
        return ResponseEntity.ok("User was added!");
    }
}
