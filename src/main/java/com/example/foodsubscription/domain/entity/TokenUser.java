package com.example.foodsubscription.domain.entity;


import com.example.foodsubscription.domain.dto.AuthorizationDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "token_user")
@NoArgsConstructor
public class TokenUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    @Column(unique = true)
    private String login;
    private String password;
    private String role;

    public TokenUser(AuthorizationDTO.Request.AddNewUserRequest user){
        this.login = user.getLogin();
        this.password = user.getPassword();
        this.role = user.getRole();
    }
}
