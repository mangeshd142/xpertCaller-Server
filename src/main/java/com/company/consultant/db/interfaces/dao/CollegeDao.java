package com.company.consultant.db.interfaces.dao;

import com.company.consultant.db.sql.entities.CollegeEntity;

import java.util.List;

public interface CollegeDao {
    List<CollegeEntity> insertColleges(List<CollegeEntity> collegeListEntity);
    List<CollegeEntity> getColleges(String collegeName);
}
