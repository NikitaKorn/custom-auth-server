package com.example.foodsubscription.domain.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Value;
import org.springframework.lang.NonNull;
import org.springframework.validation.annotation.Validated;

public enum AuthorizationDTO {;
    private interface Login { String getLogin(); }
    private interface UserEmail { String getEmail(); }
    private interface Username { String getUsername(); }
    private interface Password { String getPassword(); }
    private interface Roles { String[] getRoles(); }
    private interface Jwt { String getJwt(); }

    public enum Request{;
        @Value
        @Validated
        public static class AddNewUserRequest implements Login, Password, Roles, UserEmail {
            @NotNull @NotEmpty
            String login;
            @NonNull
            String password;
            @NonNull @Email
            String email;
            @NonNull
            String[] roles;
        }

        @Value
        public static class AuthRequest implements Username, Password {
            String username;
            String password;
        }
    }

    public enum Response {;
        @Value
        public static class AuthResponse implements Jwt {
            String jwt;
        }
    }
}
