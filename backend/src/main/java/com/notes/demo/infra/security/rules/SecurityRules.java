package com.notes.demo.infra.security.rules;

import org.springframework.stereotype.Component;

@Component("securityRules")
public class SecurityRules {
    public boolean canAccessRoute(String currentUsername, String routeUsername){
        return currentUsername.equals(routeUsername);
    }
}
