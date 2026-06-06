package com.notes.demo.services;

import com.notes.demo.domain.user.RegisterDTO;
import com.notes.demo.domain.user.UserAccount;
import com.notes.demo.domain.user.UserRole;
import com.notes.demo.exception.custom.ParamNotBlankException;
import com.notes.demo.exception.custom.UserAlreadyExistsException;
import com.notes.demo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class RegisterService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    PasswordEncoder passwordEncoder;

    public void createUser(RegisterDTO data){
        try{
            if(userRepository.findByUsername(data.getUsername()) != null ||
            userRepository.findByEmail(data.getEmail()) != null) {
                throw new UserAlreadyExistsException(
                        "Registration failed: User already exists with email or username."
                );
            }

            String encriptedPassword = passwordEncoder.encode(data.getPassword());
            UserAccount newUser = new UserAccount(
                    data.getUsername(),
                    data.getEmail(),
                    encriptedPassword, // senha encriptada
                    LocalDateTime.now(),
                    UserRole.USER
            );

            this.userRepository.save(newUser);
        } catch (UserAlreadyExistsException e) {
            throw new UserAlreadyExistsException(e.getMessage());
        }
    }
}
