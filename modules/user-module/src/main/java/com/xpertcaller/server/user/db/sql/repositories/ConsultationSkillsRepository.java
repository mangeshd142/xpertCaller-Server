package com.xpertcaller.server.user.db.sql.repositories;

import com.xpertcaller.server.user.db.sql.entities.ConsultationSkillsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ConsultationSkillsRepository extends JpaRepository<ConsultationSkillsEntity, String> {
    public ConsultationSkillsEntity findByConsultationSkillId(String collegeId);
    public Optional<ConsultationSkillsEntity> findByConsultationSkillName(String userName);
    public List<ConsultationSkillsEntity> findByConsultationCategoryEntityConsultationId(String parentId);
}
