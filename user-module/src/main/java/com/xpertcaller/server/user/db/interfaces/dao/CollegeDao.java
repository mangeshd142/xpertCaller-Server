package com.xpertcaller.server.user.db.interfaces.dao;

import com.xpertcaller.server.user.db.sql.entities.CollegeEntity;

import java.util.List;

public interface CollegeDao {
    List<CollegeEntity> insertColleges(List<CollegeEntity> collegeListEntity);
    List<CollegeEntity> getColleges(String collegeName);
}
