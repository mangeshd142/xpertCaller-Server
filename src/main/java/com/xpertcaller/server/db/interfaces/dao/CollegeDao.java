package com.xpertcaller.server.db.interfaces.dao;

import com.xpertcaller.server.db.sql.entities.CollegeEntity;

import java.util.List;

public interface CollegeDao {
    List<CollegeEntity> insertColleges(List<CollegeEntity> collegeListEntity);
    List<CollegeEntity> getColleges(String collegeName);
}
