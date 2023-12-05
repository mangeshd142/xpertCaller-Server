package com.company.consultant.db.dao;

import com.company.consultant.db.dao.interfaces.CollegeDaoInterface;
import com.company.consultant.db.entities.CollegeEntity;
import com.company.consultant.db.repositories.CollegeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CollegeDao implements CollegeDaoInterface {

    @Autowired
    CollegeRepository collegeRepository;

    @Override
    public List<CollegeEntity> insertColleges(List<CollegeEntity> collegeListEntity){
        List<CollegeEntity> collegeListEntity1 = collegeRepository.saveAll(collegeListEntity);
        return collegeListEntity1;
    }

    @Override
    public List<CollegeEntity> getColleges(String collegeName){
        return collegeRepository.findCollegeByQueryMethod(collegeName);
    }
}
