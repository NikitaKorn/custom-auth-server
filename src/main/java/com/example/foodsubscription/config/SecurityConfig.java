package com.example.foodsubscription.config;

import com.example.foodsubscription.config.jwt.JwtRequestFilter;
import com.example.foodsubscription.config.jwt.JwtUserDetailsService;
import jakarta.servlet.DispatcherType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.CorsConfigurer;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Autowired
    private JwtUserDetailsService jwtUserDetailsService;

    @Autowired
    private JwtRequestFilter jwtRequestFilter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .headers(headers -> headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable))
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .cors(CorsConfigurer::disable)
                .csrf(CsrfConfigurer::disable)
                .authorizeHttpRequests(authorize -> {
                    authorize.dispatcherTypeMatchers(DispatcherType.FORWARD, DispatcherType.ERROR).permitAll();
                    authorize.requestMatchers(
                            new AntPathRequestMatcher("/h2-console/**"),
                            new AntPathRequestMatcher("/authenticate")
                    ).permitAll();

                    authorize.requestMatchers(new AntPathRequestMatcher("/get")).hasRole("ADMIN");
                    authorize.requestMatchers(new AntPathRequestMatcher("/save_user")).hasRole("ADMIN");

                    authorize.anyRequest().denyAll();
                })
                .userDetailsService(jwtUserDetailsService)
                .addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class)
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }
}
