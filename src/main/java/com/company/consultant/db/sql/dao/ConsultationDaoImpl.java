package com.company.consultant.db.sql.dao;

import com.company.consultant.db.interfaces.dao.ConsultationDao;
import com.company.consultant.db.sql.entities.ConsultationCategoryEntity;
import com.company.consultant.db.sql.entities.ConsultationSkillsEntity;
import com.company.consultant.db.sql.repositories.ConsultationCategoryRepository;
import com.company.consultant.db.sql.repositories.ConsultationSkillsRepository;
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