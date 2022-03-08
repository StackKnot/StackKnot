package com.codeup.stackknot.repositories;

import com.codeup.stackknot.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
