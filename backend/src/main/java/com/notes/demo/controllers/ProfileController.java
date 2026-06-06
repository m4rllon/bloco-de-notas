package com.notes.demo.controllers;

import com.notes.demo.domain.profile.ProfileResponse;
import com.notes.demo.domain.user.UserProfileResponse;
import com.notes.demo.services.ProfileService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

@RestController
public class ProfileController {
    @Autowired
    ProfileService profileService;

    @GetMapping("/api/profile/{username}")
    public ResponseEntity<ProfileResponse> getProfile(
            @Valid @PathVariable String username){
        try {
            ProfileResponse profileResponse = profileService.getProfile(username);
            return ResponseEntity.ok(profileResponse);
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }
}
