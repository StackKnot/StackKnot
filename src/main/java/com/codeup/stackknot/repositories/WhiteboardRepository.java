package com.codeup.stackknot.repositories;
import com.codeup.stackknot.models.Whiteboard;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WhiteboardRepository extends JpaRepository<Whiteboard, Long> {
    List<Whiteboard> findAllById(Long id);
}
