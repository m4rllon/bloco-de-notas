package com.notes.demo.services;

import com.notes.demo.domain.profile.ProfileResponse;
import com.notes.demo.domain.user.UserAccount;
import com.notes.demo.domain.user.UserPrincipal;
import com.notes.demo.exception.custom.UserNotFoundException;
import com.notes.demo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProfileService {
    @Autowired
    private UserRepository userRepository;

    public ProfileResponse getProfile(String username){
        try {
            var user = userRepository.findByUsername(username);

            if(user == null) throw new UserNotFoundException("User not found with username " + username);

            return new ProfileResponse(
                    user.getUsername(),
                    user.getEmail(),
                    user.getCreatedAt());
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }

    }
}
