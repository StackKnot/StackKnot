package com.codeup.stackknot.repositories;
import com.codeup.stackknot.models.Card;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CardRepository extends JpaRepository<Card, Long> {
}
