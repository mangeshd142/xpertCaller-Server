package com.company.consultant.db.dao.interfaces;

import com.company.consultant.db.entities.ConsultationCategoryEntity;
import com.company.consultant.db.entities.ConsultationSkillsEntity;

import java.util.List;

public interface ConsultationDao {
    List<ConsultationCategoryEntity> insertConsultationCategories(List<ConsultationCategoryEntity> consultationCategoryEntityList);
    List<ConsultationSkillsEntity> insertConsultationSkills(List<ConsultationSkillsEntity> consultationSkillsEntityList);
    List<ConsultationCategoryEntity> getAllConsultationCategory();
    List<ConsultationSkillsEntity> getConsultationSkillsByParentId(String parentId);
}
