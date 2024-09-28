package org.inktober.repository;

import org.inktober.model.SubmissionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SubmissionRepository extends JpaRepository<SubmissionEntity, Long> {

    @Query("SELECT s FROM SubmissionEntity s WHERE s.theme.event.eventId = :eventId")
    List<SubmissionEntity> findByEventId(@Param("eventId") long eventId);
    List<SubmissionEntity> findByThemeThemeId(@Param("themeId") long themeId);
}
