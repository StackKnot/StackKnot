package com.codeup.stackknot.repositories;
import com.codeup.stackknot.models.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SetRepository extends JpaRepository<Set, Long> {
    List<Set> findAllByUserId(long user_id);
}
