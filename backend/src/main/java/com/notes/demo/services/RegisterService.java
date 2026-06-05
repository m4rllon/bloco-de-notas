package com.notes.demo.services;

import com.notes.demo.domain.user.RegisterDTO;
import com.notes.demo.domain.user.UserAccount;
import com.notes.demo.domain.user.UserRole;
import com.notes.demo.exception.custom.ParamNotBlankException;
import com.notes.demo.exception.custom.UserAlreadyExistsException;
import com.notes.demo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class RegisterService {
    @Autowired
    UserRepository userRepository;

    public void createUser(RegisterDTO data){
        try{
            if (data.getUsername() == null || data.getEmail() == null || data.getPassword() == null)
                throw new ParamNotBlankException(
                        "All fields must be filled in."
                );

            if(userRepository.findByUsername(data.getUsername()) != null ||
            userRepository.findByEmail(data.getEmail()) != null) {
                throw new UserAlreadyExistsException(
                        "Registration failed: User already exists with email or username."
                );
            }

            String encriptedPassword = new BCryptPasswordEncoder().encode(data.getPassword());
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
        } catch (ParamNotBlankException e) {
            throw new ParamNotBlankException(e.getMessage());
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }
}
