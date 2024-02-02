package com.example.foodsubscription.domain.entity;

import com.example.foodsubscription.domain.dto.AuthorizationDTO;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.Locale;
import java.util.Set;
import java.util.stream.Collectors;

@Table(name="users")
@Entity
@Data
@NoArgsConstructor
public class User {
    @Transient
    private final static String PREFIX = "ROLE_";
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String login;
    @Column(name="email")
    private String email;

    @Column(name="password")
    private String password;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;

    public User(AuthorizationDTO.Request.AddNewUserRequest user){
        this.login = user.getLogin();
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.roles = Arrays.stream(user.getRoles()).map(el -> new Role(makeRole(el))).collect(Collectors.toSet());
    }

    private String makeRole(String role){
        return PREFIX.concat(role.toUpperCase(Locale.ROOT));
    }
}