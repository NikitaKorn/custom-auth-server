package com.example.foodsubscription.service.response;

import com.example.foodsubscription.domain.dto.AuthorizationDTO;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

public class ResponseEntityPatterns {

    public static ResponseEntity<?> anonymousAccess(){
        return ResponseEntity.status(HttpStatusCode.valueOf(401)).body("Invalid credentials");
    }

    public static ResponseEntity<?> okWithBody(String response){
        return ResponseEntity.ok(response);
    }

    public static ResponseEntity<?> okWithBody(AuthorizationDTO.Response.AuthResponse response){
        return ResponseEntity.ok(response);
    }
}
