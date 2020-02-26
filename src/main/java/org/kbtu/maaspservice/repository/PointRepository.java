package org.kbtu.maaspservice.repository;

import org.kbtu.maaspservice.model.Point;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PointRepository extends JpaRepository<Point, UUID> {
}
