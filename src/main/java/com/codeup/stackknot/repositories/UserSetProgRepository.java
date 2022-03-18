package com.codeup.stackknot.repositories;

import com.codeup.stackknot.models.Set;
import com.codeup.stackknot.models.User;
import com.codeup.stackknot.models.UserSetProg;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserSetProgRepository extends JpaRepository<UserSetProg, Long> {
    UserSetProg findByUserAndAndSet(User user, Set set);
}
