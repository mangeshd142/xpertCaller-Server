package com.xpertcaller.server.user.db.sql.dao;

import com.xpertcaller.server.user.db.interfaces.dao.EducationDetailsDao;
import com.xpertcaller.server.user.db.sql.repositories.EducationDetailsRepository;
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
