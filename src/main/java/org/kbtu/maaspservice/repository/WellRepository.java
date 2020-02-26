package org.kbtu.maaspservice.repository;

import org.kbtu.maaspservice.model.Well;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface WellRepository extends JpaRepository<Well, UUID> {
}
