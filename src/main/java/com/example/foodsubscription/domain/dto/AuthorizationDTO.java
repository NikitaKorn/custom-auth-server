package com.example.foodsubscription.domain.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Value;
import org.springframework.lang.NonNull;
import org.springframework.validation.annotation.Validated;

public enum AuthorizationDTO {;
    private interface Login { String getLogin(); }
    private interface Username { String getUsername(); }
    private interface Password { String getPassword(); }
    private interface Role { String getRole(); }
    private interface Jwt { String getJwt(); }

    public enum Request{;
        @Value
        @Validated
        public static class AddNewUserRequest implements Login, Password, Role {
            @NotNull @NotEmpty
            String login;
            @NonNull
            String password;
            @NonNull
            String role;
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
