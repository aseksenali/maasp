package org.kbtu.maaspservice.repository;

import org.kbtu.maaspservice.model.PointInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PointInfoRepository extends JpaRepository<PointInfo, UUID> {
}
