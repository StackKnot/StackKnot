package com.codeup.stackknot.repositories;
import com.codeup.stackknot.models.Whiteboard;

import org.springframework.data.jpa.repository.JpaRepository;

public interface WhiteboardRepository extends JpaRepository<Whiteboard, Long> {
}
