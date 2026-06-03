package com.notes.demo.infra.security.rules;

import com.notes.demo.exception.custom.UserWithoutPermissionException;
import org.springframework.stereotype.Component;

@Component("securityRules")
public class SecurityRules {
    public boolean canAccessRoute(String currentUsername, String routeUsername, String authorities){
        if (currentUsername.equals(routeUsername) || authorities.contains("ROLE_ADMIN")) return true;
        throw new UserWithoutPermissionException();
    }

    public boolean isAdmin(String authorities){
        if(authorities.contains("ROLE_ADMIN")) return true;
        throw new UserWithoutPermissionException();
    }
}
