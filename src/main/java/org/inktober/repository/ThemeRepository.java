package org.inktober.repository;

import org.inktober.model.ThemeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ThemeRepository extends JpaRepository<ThemeEntity, Long> {
    List<ThemeEntity> findByEventEventId(long eventId);
}
