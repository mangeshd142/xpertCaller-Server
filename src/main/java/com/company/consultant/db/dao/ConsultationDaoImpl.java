package com.company.consultant.db.dao;

import com.company.consultant.db.dao.interfaces.ConsultationDao;
import com.company.consultant.db.entities.CollegeEntity;
import com.company.consultant.db.entities.ConsultationCategory;
import com.company.consultant.db.entities.ConsultationSkills;
import com.company.consultant.db.repositories.ConsultationCategoryRepository;
import com.company.consultant.db.repositories.ConsultationSkillsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ConsultationDaoImpl implements ConsultationDao {

    @Autowired
    ConsultationCategoryRepository consultationCategoryRepository;

    @Autowired
    ConsultationSkillsRepository consultationSkillsRepository;

    @Override
    public List<ConsultationCategory> insertConsultationCategories(List<ConsultationCategory> consultationCategoryList){
        return consultationCategoryRepository.saveAll(consultationCategoryList);
    }

    @Override
    public List<ConsultationSkills> insertConsultationSkills(List<ConsultationSkills> consultationSkillsList){
        return consultationSkillsRepository.saveAll(consultationSkillsList);
    }

    @Override
    public List<ConsultationCategory> getAllConsultationCategory(){
        return  consultationCategoryRepository.findAll();
    }

    @Override
    public List<ConsultationSkills> getConsultationSkillsByParentId(String parentId){
        return  consultationSkillsRepository.findByParentId(parentId);
    }
}
