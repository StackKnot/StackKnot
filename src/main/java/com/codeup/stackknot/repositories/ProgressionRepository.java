package com.codeup.stackknot.repositories;

import com.codeup.stackknot.models.Progression;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProgressionRepository extends JpaRepository<Progression, Long> {
    Progression getByStatus(String status);
}
