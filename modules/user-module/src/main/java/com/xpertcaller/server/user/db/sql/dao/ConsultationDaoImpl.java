package com.xpertcaller.server.user.db.sql.dao;

import com.xpertcaller.server.user.db.interfaces.dao.ConsultationDao;
import com.xpertcaller.server.user.db.sql.entities.ConsultationCategoryEntity;
import com.xpertcaller.server.user.db.sql.entities.ConsultationSkillsEntity;
import com.xpertcaller.server.user.db.sql.repositories.ConsultationCategoryRepository;
import com.xpertcaller.server.user.db.sql.repositories.ConsultationSkillsRepository;
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
        return  consultationSkillsRepository.findByConsultationCategoryEntityConsultationId(parentId);
    }
}
