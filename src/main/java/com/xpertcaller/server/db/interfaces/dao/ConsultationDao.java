package com.xpertcaller.server.db.interfaces.dao;

import com.xpertcaller.server.db.sql.entities.ConsultationCategoryEntity;
import com.xpertcaller.server.db.sql.entities.ConsultationSkillsEntity;

import java.util.List;

public interface ConsultationDao {
    List<ConsultationCategoryEntity> insertConsultationCategories(List<ConsultationCategoryEntity> consultationCategoryEntityList);
    List<ConsultationSkillsEntity> insertConsultationSkills(List<ConsultationSkillsEntity> consultationSkillsEntityList);
    List<ConsultationCategoryEntity> getAllConsultationCategory();
    List<ConsultationSkillsEntity> getConsultationSkillsByParentId(String parentId);
}
