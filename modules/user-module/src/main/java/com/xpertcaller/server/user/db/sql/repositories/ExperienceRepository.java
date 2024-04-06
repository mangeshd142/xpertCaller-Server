package com.xpertcaller.server.user.db.sql.repositories;

import com.xpertcaller.server.user.db.sql.entities.profileEntities.ExperienceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExperienceRepository extends JpaRepository<ExperienceEntity, String> {
}
