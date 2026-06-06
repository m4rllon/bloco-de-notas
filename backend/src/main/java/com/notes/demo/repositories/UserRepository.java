package com.notes.demo.repositories;

import com.notes.demo.domain.user.UserAccount;
import com.notes.demo.domain.user.UserPrincipal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserRepository extends JpaRepository<UserAccount, Long> {
    UserAccount findByUsername(String username);

    UserAccount findByEmail(String email);

}
