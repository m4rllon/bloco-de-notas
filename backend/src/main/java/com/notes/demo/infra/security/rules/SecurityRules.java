package com.notes.demo.infra.security.rules;

import org.springframework.stereotype.Component;

@Component("securityRules")
public class SecurityRules {
    public boolean canAccessRoute(String currentUsername, String routeUsername, String authorities){
        return currentUsername.equals(routeUsername) || authorities.contains("ROLE_ADMIN");
    }

    public boolean isAdmin(String authorities){
        return authorities.contains("ROLE_ADMIN");
    }
}
