package com.company.consultant.db.dao;

import com.company.consultant.db.dao.interfaces.ConsultationDao;
import com.company.consultant.db.entities.ConsultationCategoryEntity;
import com.company.consultant.db.entities.ConsultationSkillsEntity;
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
    public List<ConsultationCategoryEntity> insertConsultationCategories(List<ConsultationCategoryEntity> consultationCategoryEntityList){
        return consultationCategoryRepository.saveAll(consultationCategoryEntityList);
    }

    @Override
    public List<ConsultationSkillsEntity> insertConsultationSkills(List<ConsultationSkillsEntity> consultationSkillsEntityList){
        return consultationSkillsRepository.saveAll(consultationSkillsEntityList);
    }

    @Override
    public List<ConsultationCategoryEntity> getAllConsultationCategory(){
        return  consultationCategoryRepository.findAll();
    }

    @Override
    public List<ConsultationSkillsEntity> getConsultationSkillsByParentId(String parentId){
        return  consultationSkillsRepository.findByParentId(parentId);
    }
}
