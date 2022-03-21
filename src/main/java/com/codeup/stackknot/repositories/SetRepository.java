package com.codeup.stackknot.repositories;
import com.codeup.stackknot.models.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SetRepository extends JpaRepository<Set, Long> {
    List<Set> findAllByUserId(long user_id);

    Set getByTitle(String title);

    Set getByDescription(String description);

    @Query(value = "SELECT * FROM sets WHERE MATCH(title, description) AGAINST (?1)", nativeQuery = true)
    List<Set> search(String keyword);

}
