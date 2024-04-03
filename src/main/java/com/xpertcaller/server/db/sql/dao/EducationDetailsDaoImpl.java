package com.xpertcaller.server.db.sql.dao;

import com.xpertcaller.server.db.interfaces.dao.EducationDetailsDao;
import com.xpertcaller.server.db.sql.repositories.EducationDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EducationDetailsDaoImpl  implements EducationDetailsDao {

    @Autowired
    EducationDetailsRepository educationDetailsRepository;

    @Override
    public void deleteEducationDetails(String educationDetailId){
        educationDetailsRepository.deleteById(educationDetailId);
    }
}
