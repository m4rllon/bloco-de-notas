package com.notes.demo.repositories;

import com.notes.demo.domain.user.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserAccount, Long> {

}
