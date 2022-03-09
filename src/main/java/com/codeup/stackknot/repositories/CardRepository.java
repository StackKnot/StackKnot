package com.codeup.stackknot.repositories;
import com.codeup.stackknot.models.Card;

import com.codeup.stackknot.models.Set;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CardRepository extends JpaRepository<Card, Long> {
    List<Card> findAllBySetId(long set_id);
}
