package com.example.foodsubscription.config.jwt;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Slf4j
@Component
public class JwtRequestFilter extends OncePerRequestFilter {
    @Autowired
    protected UserDetailsService usrDetailsService;
    @Autowired
    protected JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        final String authHeader = request.getHeader("Authorization");

        String username = null;
        String jwt = null;

        if(validateAuthorizationHeader(authHeader)){
            jwt = extractJwtTokenFromAuthorizationHeader(authHeader);
            username = extractUsernameFromJwtToken(jwt);
        }

        if(validateAuthentication(jwt, username)){
            UserDetails userDetails = usrDetailsService.loadUserByUsername(username);
            validateTokenAndSetAuthentication(jwt, userDetails, request);
        }

        filterChain.doFilter(request, response);
    }

    private boolean validateAuthorizationHeader(String authHeader){
        return authHeader != null && authHeader.startsWith("Bearer ");
    }

    private String extractJwtTokenFromAuthorizationHeader(String authHeader){
        return authHeader.substring(7);
    }

    private String extractUsernameFromJwtToken(String jwt){
        String username = null;
        try {
            username = jwtUtil.extractUsername(jwt);
        } catch (Exception ex){
            log.error("Corrupted token");
        }
        return username;
    }

    private boolean validateAuthentication(String jwt, String username){
        return jwt != null && username != null && SecurityContextHolder.getContext().getAuthentication() == null;
    }

    private void validateTokenAndSetAuthentication(String jwt, UserDetails userDetails, HttpServletRequest request){
        if(jwtUtil.validateToken(jwt, userDetails)){
            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails,
                    null, userDetails.getAuthorities());
            usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
        }
    }
}
