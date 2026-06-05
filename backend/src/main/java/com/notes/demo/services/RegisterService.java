package com.notes.demo.services;

import com.notes.demo.domain.user.RegisterDTO;
import com.notes.demo.domain.user.UserAccount;
import com.notes.demo.repositories.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class RegisterService {
    UserRepository userRepository;

    public void createUser(RegisterDTO data){

//        if(data.username() )

        String encriptedPassword = new BCryptPasswordEncoder().encode(data.password());
        UserAccount newUser = new UserAccount(
                data.username(),
                data.email(),
                encriptedPassword, // senha encriptada
                LocalDateTime.now(),
                data.role()
        );

        this.userRepository.save(newUser);
    }
}
