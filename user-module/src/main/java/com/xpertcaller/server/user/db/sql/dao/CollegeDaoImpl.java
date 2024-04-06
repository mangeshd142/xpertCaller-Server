package com.xpertcaller.server.user.db.sql.dao;

import com.xpertcaller.server.user.db.interfaces.dao.CollegeDao;
import com.xpertcaller.server.user.db.sql.entities.CollegeEntity;
import com.xpertcaller.server.user.db.sql.repositories.CollegeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class CollegeDaoImpl implements CollegeDao {

    @Autowired
    CollegeRepository collegeRepository;

    @Transactional
    @Override
    public List<CollegeEntity> insertColleges(List<CollegeEntity> collegeListEntity){
        return collegeRepository.saveAll(collegeListEntity);
    }

    @Override
    public List<CollegeEntity> getColleges(String collegeName){
        return collegeRepository.findCollegeByQueryMethod(collegeName);
    }
}
