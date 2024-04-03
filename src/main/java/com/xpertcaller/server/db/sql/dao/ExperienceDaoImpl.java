package com.xpertcaller.server.db.sql.dao;

import com.xpertcaller.server.db.interfaces.dao.ExperienceDao;
import com.xpertcaller.server.db.sql.repositories.ExperienceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ExperienceDaoImpl implements ExperienceDao {

    @Autowired
    ExperienceRepository experienceRepository;

    @Override
    public void deleteExperience(String experienceId){
         experienceRepository.deleteById(experienceId);
    }
}
