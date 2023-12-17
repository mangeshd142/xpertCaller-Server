package com.company.consultant.db.dao;

import com.company.consultant.db.repositories.CollegeRepository;
import com.company.consultant.db.dao.interfaces.CollegeDao;
import com.company.consultant.db.entities.CollegeEntity;
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
