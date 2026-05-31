package com.notes.demo.controllers;

import com.notes.demo.domain.user.AuthenticationDTO;
import com.notes.demo.domain.user.LoginResponseDTO;
import com.notes.demo.domain.user.RegisterDTO;
import com.notes.demo.domain.user.UserAccount;
import com.notes.demo.exception.custom.InvalidCredentialsException;
import com.notes.demo.exception.custom.UserAlreadyExistsException;
import com.notes.demo.repositories.UserRepository;
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
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthenticationDTO data){
        // vamos validar se as credenciais estão certas
        try{
            UsernamePasswordAuthenticationToken loginPassword = null;

            if(data.username() != null)
                loginPassword = new UsernamePasswordAuthenticationToken(
                        data.username(), data.password()); // criptografa a senha
            else if(data.email() != null){
                loginPassword = new UsernamePasswordAuthenticationToken(
                        data.email(), data.password()); // criptografa a senha
            }

            var auth = this.authenticationManager.authenticate(loginPassword); // realiza a autenticação

            var token = tokenService.generateToken((UserAccount) auth.getPrincipal());

            return ResponseEntity.ok(new LoginResponseDTO(token));
        } catch (RuntimeException e) {
            throw new InvalidCredentialsException(e.getMessage());
        }
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid RegisterDTO data) {
        try {
                String encriptedPassword = new BCryptPasswordEncoder().encode(data.password());
                UserAccount newUser = new UserAccount(
                        data.username(),
                        data.email(),
                        encriptedPassword, // senha encriptada
                        LocalDateTime.now(),
                        data.role()
                );

                this.userRepository.save(newUser);

                return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            throw new UserAlreadyExistsException("Username or email already exists");
        }
    }
}
