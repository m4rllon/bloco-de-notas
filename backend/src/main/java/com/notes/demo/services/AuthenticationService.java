package com.notes.demo.services;

import com.notes.demo.domain.user.AuthenticationDTO;
import com.notes.demo.domain.user.LoginResponseDTO;
import com.notes.demo.domain.user.UserAccount;
import com.notes.demo.exception.custom.InvalidCredentialsException;
import com.notes.demo.exception.custom.ParamNotBlankException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    TokenService tokenService;

    public ResponseEntity login(AuthenticationDTO data){
        try{
            if(data.username() == null && data.email() == null)
                throw new ParamNotBlankException("You need to provide at least your email address or username.");

            UsernamePasswordAuthenticationToken loginPassword = null;

            if (data.username() != null)
                loginPassword = new UsernamePasswordAuthenticationToken(
                        data.username(), data.password()); // criptografa a senha
            else
                loginPassword = new UsernamePasswordAuthenticationToken(
                        data.email(), data.password()); // criptografa a senha

            var auth = this.authenticationManager.authenticate(loginPassword); // realiza a autenticação

            var token = tokenService.generateToken((UserAccount) auth.getPrincipal());

            return ResponseEntity.ok(new LoginResponseDTO(token));
        } catch (RuntimeException e) {
            throw new InvalidCredentialsException(e.getMessage());
        }
    }
}
