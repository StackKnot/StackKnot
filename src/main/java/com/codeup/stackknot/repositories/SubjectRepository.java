package com.codeup.stackknot.repositories;
import com.codeup.stackknot.models.Subject;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SubjectRepository extends JpaRepository<Subject, Long> {
    Subject findByTitle(String title);
}
