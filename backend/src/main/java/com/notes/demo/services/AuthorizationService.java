package com.notes.demo.services;

import com.notes.demo.exception.custom.InvalidCredentialsException;
import com.notes.demo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthorizationService implements UserDetailsService {

    @Autowired
    UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        // método para carregar usuário pelo user
        try{
            UserDetails user = repository.findByUsername(login);
            if(user != null) return user;
            else {
                user = repository.findByEmail(login);
                return user;
            }
        } catch (RuntimeException e) {
            throw new InvalidCredentialsException("E-mail ou senha inválidos." + e.getMessage());
        }
    }
}
