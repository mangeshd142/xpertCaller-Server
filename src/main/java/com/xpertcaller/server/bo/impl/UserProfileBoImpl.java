package com.xpertcaller.server.bo.impl;

import com.xpertcaller.server.beans.user.*;
import com.xpertcaller.server.bo.interfaces.UserProfileBo;
import com.xpertcaller.server.db.interfaces.dao.UserDao;
import com.xpertcaller.server.db.interfaces.dao.UserProfileDao;
import com.xpertcaller.server.db.sql.entities.UserEntity;
import com.xpertcaller.server.db.sql.entities.profileEntities.UserProfileEntity;
import com.xpertcaller.server.db.sql.entities.profileEntities.CategoryEntity;
import com.xpertcaller.server.db.sql.entities.profileEntities.AddressEntity;
import com.xpertcaller.server.db.sql.entities.profileEntities.EducationDetailsEntity;
import com.xpertcaller.server.db.sql.entities.profileEntities.ExperienceEntity;
import com.xpertcaller.server.db.sql.repositories.AddressRepository;
import com.xpertcaller.server.db.sql.repositories.CategoryRepository;
import com.xpertcaller.server.db.sql.repositories.EducationDetailsRepository;
import com.xpertcaller.server.db.sql.repositories.ExperienceRepository;
import com.xpertcaller.server.exception.userdefined.BusinessException;
import com.xpertcaller.server.service.UserServiceImpl;
import com.xpertcaller.server.util.CommonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class UserProfileBoImpl implements UserProfileBo {

    @Autowired
    UserProfileDao userProfileDao;
    @Autowired
    AddressRepository addressRepository;
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    EducationDetailsRepository educationDetailsRepository;
    @Autowired
    ExperienceRepository experienceRepository;
    @Autowired
    UserDao userDao;
    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public ProfileDetails addProfileDetails(ProfileDetails profileDetails) throws BusinessException {
        try {
            User user = CommonUtil.getCurrentUser();
            UserEntity userEntity = userDao.getUserById(user.getUserId());
            UserProfileEntity userProfileEntity = userProfileDao.getProfileByUser(userEntity);
            userProfileEntity = convertUserProfileToUseProfileEntity(profileDetails, userProfileEntity);
            userProfileEntity.setUserEntity(userEntity);

            userProfileDao.saveUserProfile(userProfileEntity);
        }catch (Exception e){
            logger.error("Error while adding profile details ", e);
            throw new BusinessException("Error while adding profile details");
        }
        return profileDetails;
    }

    private UserProfileEntity convertUserProfileToUseProfileEntity(ProfileDetails profileDetails, UserProfileEntity userProfileEntity){
        if(userProfileEntity == null){
            userProfileEntity = new UserProfileEntity();
            String profileId = UUID.randomUUID().toString();
            userProfileEntity.setProfileId(profileId);
        }

        userProfileEntity.setAbout(profileDetails.getAbout());
        userProfileEntity.setLanguages(profileDetails.getLanguages());
        userProfileEntity.setProfilePic(profileDetails.getProfilePic());
        userProfileEntity.setFiles(profileDetails.getFiles());

        Address address = profileDetails.getAddress();
        if(address != null) {
            AddressEntity addressEntity = new AddressEntity(address.getStreet(), address.getCity(), address.getState(),
                    address.getZipCode(), address.getLongitude(), address.getLatitude());
            addressEntity = addressRepository.save(addressEntity);
            userProfileEntity.setAddressEntity(addressEntity);
        }

        Category category = profileDetails.getCategory();
        if(category != null) {
            CategoryEntity categoryEntity = new CategoryEntity(category.getCategory(),
                    category.getSkills());
            categoryEntity = categoryRepository.save(categoryEntity);
            userProfileEntity.setCategoryEntity(categoryEntity);
        }

        List<EducationDetails> educationDetails = profileDetails.getEducationDetails();
        if(educationDetails != null) {
            List<EducationDetailsEntity> educationDetailsEntities = new ArrayList<>();
            for (EducationDetails educationDetail : educationDetails) {
                EducationDetailsEntity educationDetailsEntity = new EducationDetailsEntity(educationDetail.getDegree(), educationDetail.getCollege());
                educationDetailsEntity.setUserProfileEntity(userProfileEntity);
                educationDetailsEntities.add(educationDetailsEntity);
            }
            if (!educationDetailsEntities.isEmpty()) {
                userProfileEntity.setEducationDetailEntities(educationDetailsEntities);
            }
        }

        List<Experience> experiences = profileDetails.getExperiences();
        if(experiences != null) {
            List<ExperienceEntity> experienceEntities = new ArrayList<>();
            for (Experience experience : experiences) {
                ExperienceEntity experienceEntity = new ExperienceEntity(experience.getCompanyName(), experience.getRole(),
                        experience.getDetails(), experience.getYears());
                experienceEntity.setUserProfileEntity(userProfileEntity);
                experienceEntities.add(experienceEntity);
            }
            if (!experienceEntities.isEmpty()) {
                userProfileEntity.setExperienceEntities(experienceEntities);
            }
        }

        return userProfileEntity;
    }
}
