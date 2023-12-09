package com.company.consultant.db.repositories;

import com.company.consultant.db.entities.ConsultationSkills;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ConsultationSkillsRepository extends JpaRepository<ConsultationSkills, String> {
    public ConsultationSkills findByConsultationSkillId(String collegeId);
    public Optional<ConsultationSkills> findByConsultationSkillName(String userName);
    public List<ConsultationSkills> findByParentId(String parentId);
}
