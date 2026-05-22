package com.notes.demo.controllers;

import com.notes.demo.domain.user.UserProfileResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;

@RestController
public class ProfileController {
    @GetMapping("/api/profile")
    public UserProfileResponse getProfile(@AuthenticationPrincipal OAuth2User principal){
        String name = principal.getAttribute("name");
        String email = principal.getAttribute("email");
        String picture = principal.getAttribute("picture");

        return new UserProfileResponse(name, email, picture);
    }
}
