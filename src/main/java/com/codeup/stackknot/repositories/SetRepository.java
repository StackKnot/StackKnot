package com.codeup.stackknot.repositories;
import com.codeup.stackknot.models.Set;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SetRepository extends JpaRepository<Set, Long> {
}
