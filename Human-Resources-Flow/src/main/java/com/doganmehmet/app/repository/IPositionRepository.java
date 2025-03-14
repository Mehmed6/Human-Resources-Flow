package com.doganmehmet.app.repository;

import com.doganmehmet.app.entity.Position;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPositionRepository extends JpaRepository<Position, Long> {
    Position findByTitle(String title);
}
