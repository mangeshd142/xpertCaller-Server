package com.xpertcaller.server.db.sql.dao;

import com.xpertcaller.server.db.sql.repositories.CollegeRepository;
import com.xpertcaller.server.db.interfaces.dao.CollegeDao;
import com.xpertcaller.server.db.sql.entities.CollegeEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CollegeDaoImpl implements CollegeDao {

    @Autowired
    CollegeRepository collegeRepository;

    @Override
    public List<CollegeEntity> insertColleges(List<CollegeEntity> collegeListEntity){
        return collegeRepository.saveAll(collegeListEntity);
    }

    @Override
    public List<CollegeEntity> getColleges(String collegeName){
        return collegeRepository.findCollegeByQueryMethod(collegeName);
    }
}
