package com.codeup.stackknot.repositories;

import com.codeup.stackknot.models.Set;
import com.codeup.stackknot.models.User;
import com.codeup.stackknot.models.UserSetProg;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserSetProgRepository extends JpaRepository<UserSetProg, Long> {
    UserSetProg findByUserAndSet(User user, Set set);
    List<UserSetProg> findAllByUser(User user);
}
