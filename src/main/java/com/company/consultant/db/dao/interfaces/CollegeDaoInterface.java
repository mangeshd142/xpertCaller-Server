package com.company.consultant.db.dao.interfaces;

import com.company.consultant.db.entities.CollegeEntity;

import java.util.List;

public interface CollegeDaoInterface {
    List<CollegeEntity> insertColleges(List<CollegeEntity> collegeListEntity);
    List<CollegeEntity> getColleges(String collegeName);
}
