package com.xpertcaller.server.expertdata.boimpl;


import com.xpertcaller.server.expertdata.beans.ExpertDetails;
import com.xpertcaller.server.expertdata.bo.ExpertDetailBo;
import com.xpertcaller.server.user.db.interfaces.dao.UserDao;
import com.xpertcaller.server.user.db.interfaces.dao.UserProfileDao;
import com.xpertcaller.server.user.db.sql.entities.UserEntity;
import com.xpertcaller.server.user.db.sql.entities.profileEntities.EducationDetailsEntity;
import com.xpertcaller.server.user.db.sql.entities.profileEntities.ExperienceEntity;
import com.xpertcaller.server.user.db.sql.entities.profileEntities.UserProfileEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@Component
public class ExpertDetailBoImpl implements ExpertDetailBo {

    @Autowired
    UserDao userDao;

    @Autowired
    UserProfileDao userProfileDao;

    @Override
    public ExpertDetails fetchExpertDetails(String id){
        UserEntity userEntity = userDao.getUserById(id);
        return getExpertDetailByUser(userEntity);
    }

    private ExpertDetails getExpertDetailByUser(UserEntity userEntity){
        UserProfileEntity userProfileEntity = userProfileDao.getProfileByUser(userEntity);

        ExpertDetails expertDetails = new ExpertDetails();

        expertDetails.setId(userEntity.getUserId());
        expertDetails.setAbout(userEntity.getAbout());
        expertDetails.setGender(userEntity.getGender());
        expertDetails.setName(userEntity.getName());
        expertDetails.setCity(userEntity.getAddressEntity()!=null ? userEntity.getAddressEntity().getCity() : null);
        if(userProfileEntity != null) {
            expertDetails.setCategory(userProfileEntity.getCategoryEntity() != null ? userProfileEntity.getCategoryEntity().getCategory() : null);
            expertDetails.setSkills(userProfileEntity.getCategoryEntity() != null ? userProfileEntity.getCategoryEntity().getSkills() : null);
            expertDetails.setFiles(userProfileEntity.getFiles());
            expertDetails.setLanguages(userProfileEntity.getLanguages());
            List<EducationDetailsEntity> educationDetailsEntityList = userProfileEntity.getEducationDetailEntities();
            List<String> degrees = new ArrayList<>();
            educationDetailsEntityList.forEach(educationDetailsEntity -> {
                degrees.add(educationDetailsEntity.getDegree());
            });
            expertDetails.setDegree(degrees);
            List<ExperienceEntity> experienceEntities = userProfileEntity.getExperienceEntities();

            AtomicReference<Float> experience = new AtomicReference<>((float) 0);
        /*experienceEntities.forEach(experienceEntity -> {
            if(experienceEntity.getYears() != null )
                experience.set(experience.get() + Float.parseFloat(experienceEntity.getYears()));
        });*/
            expertDetails.setExperience(experience.get());
        }

        return expertDetails;
    }

    @Override
    public List<ExpertDetails> fetchAllExpertDetails(){
        List<UserEntity> userEntityList = userDao.getAllUsers();
        List<ExpertDetails> expertDetails = new ArrayList<>();
        userEntityList.forEach(userEntity -> {
            expertDetails.add(getExpertDetailByUser(userEntity));
        });
        return expertDetails;
    }
}
