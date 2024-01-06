package com.example.foodsubscription.contoller;

import com.example.foodsubscription.domain.dto.AuthorizationDTO;
import com.example.foodsubscription.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class MainController {
    @Autowired
    private AuthService authService;

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<?> authenticate(@RequestBody AuthorizationDTO.Request.AuthRequest authRequest){
        return authService.authenticateUser(authRequest);
    }

    @RequestMapping(value = "/save_user", method = RequestMethod.POST)
    public ResponseEntity<?> saveUser(@RequestBody AuthorizationDTO.Request.AddNewUserRequest user){
        return authService.saveUser(user);
    }

    @GetMapping("/get")
    public String get(){
        return "zhopa";
    }
}
