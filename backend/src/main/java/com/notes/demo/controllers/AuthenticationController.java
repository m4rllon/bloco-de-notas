package com.notes.demo.controllers;

import com.notes.demo.domain.user.AuthenticationDTO;
import com.notes.demo.domain.user.LoginResponseDTO;
import com.notes.demo.domain.user.RegisterDTO;
import com.notes.demo.domain.user.UserAccount;
import com.notes.demo.exception.custom.InvalidCredentialsException;
import com.notes.demo.exception.custom.ParamNotBlankException;
import com.notes.demo.exception.custom.UserAlreadyExistsException;
import com.notes.demo.repositories.UserRepository;
import com.notes.demo.services.AuthenticationService;
import com.notes.demo.services.RegisterService;
import com.notes.demo.services.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("auth")
public class AuthenticationController {
    @Autowired
    private RegisterService registerService;
    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthenticationDTO data){
        return authenticationService.login(data);
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid RegisterDTO data) {
        try {
                this.registerService.createUser(data);

                return ResponseEntity.ok().build();
        } catch (UserAlreadyExistsException e) {
            throw new UserAlreadyExistsException(e.getMessage());
        } catch (ParamNotBlankException e) {
            throw new ParamNotBlankException(e.getMessage());
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }
}
