package com.notes.demo.controllers;

import com.notes.demo.domain.user.AuthenticationDTO;
import com.notes.demo.domain.user.RegisterDTO;
import com.notes.demo.services.LoginService;
import com.notes.demo.services.RegisterService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;



@RestController
@RequestMapping("auth")
@CrossOrigin(origins = "*")
public class AuthenticationController {
    @Autowired
    private RegisterService registerService;
    @Autowired
    private LoginService authenticationService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthenticationDTO data){
        return authenticationService.login(data);
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid RegisterDTO data, UriComponentsBuilder uriComponentsBuilder) {
        try {
                this.registerService.createUser(data);

                var uri = uriComponentsBuilder
                        .path("/api/profile/{username}")
                        .buildAndExpand(data.getUsername())
                        .toUri();

                return ResponseEntity.created(uri).build();
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }
}
