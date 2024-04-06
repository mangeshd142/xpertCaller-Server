package com.xpertcaller.server.user.db.sql.repositories;

import com.xpertcaller.server.user.db.sql.entities.ConsultationCategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ConsultationCategoryRepository  extends JpaRepository<ConsultationCategoryEntity, String> {
    public ConsultationCategoryEntity findByConsultationId(String collegeId);
    public Optional<ConsultationCategoryEntity> findByConsultationName(String userName);
}
