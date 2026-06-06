package com.notes.demo.services;

import com.notes.demo.domain.user.UserAccount;
import com.notes.demo.domain.user.UserPrincipal;
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
    public UserDetails loadUserByUsername(String login)
            throws UsernameNotFoundException {
        // método para carregar usuário pelo user
        UserAccount user = repository.findByUsername(login);

        if(user == null){
            user = repository.findByEmail(login);
            if(user == null)
                throw new UsernameNotFoundException("User not found.");
        }

        return new UserPrincipal(user);
    }
}
