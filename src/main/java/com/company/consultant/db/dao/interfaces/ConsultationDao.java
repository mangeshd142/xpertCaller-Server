package com.company.consultant.db.dao.interfaces;

import com.company.consultant.db.entities.ConsultationCategory;
import com.company.consultant.db.entities.ConsultationSkills;

import java.util.List;

public interface ConsultationDao {
    List<ConsultationCategory> insertConsultationCategories(List<ConsultationCategory> consultationCategoryList);
    List<ConsultationSkills> insertConsultationSkills(List<ConsultationSkills> consultationSkillsList);
    List<ConsultationCategory> getAllConsultationCategory();
    List<ConsultationSkills> getConsultationSkillsByParentId(String parentId);
}
