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
            logger.debug("adding Profile details");
            User user = CommonUtil.getCurrentUser();
            UserEntity userEntity = userDao.getUserById(user.getUserId());
            UserProfileEntity userProfileEntity = userProfileDao.getProfileByUser(userEntity);
            userProfileEntity = createOrUpdateUserProfileEntity(profileDetails, userProfileEntity);
            userProfileEntity.setUserEntity(userEntity);

            UserProfileEntity updatedUserProfileEntity = userProfileDao.saveUserProfile(userProfileEntity);
            return convertUserProfileEntityToProfileDetails(updatedUserProfileEntity);
        }catch (Exception e){
            logger.error("Error while adding profile details ", e);
            throw new BusinessException("Error while adding profile details");
        }
    }

    @Override
    public ProfileDetails updateProfilePictureId(String profileImageId) throws BusinessException {
        try {
            UserProfileEntity userProfileEntity = getCurrentUserProfileEntity();
            userProfileEntity.setProfilePic(profileImageId);
            UserProfileEntity updatedUserProfileEntity = userProfileDao.saveUserProfile(userProfileEntity);
            return convertUserProfileEntityToProfileDetails(updatedUserProfileEntity);
        }catch (Exception e){
            logger.error("Error while adding profile pic id ", e);
            throw new BusinessException("Error while adding profile pic id");
        }
    }

    @Override
    public ProfileDetails updateDocumentIds(List<String> profilePicIds) throws BusinessException {
        try {
            UserProfileEntity userProfileEntity = getCurrentUserProfileEntity();
            userProfileEntity.setFiles(profilePicIds);
            UserProfileEntity updatedUserProfileEntity = userProfileDao.saveUserProfile(userProfileEntity);
            return convertUserProfileEntityToProfileDetails(updatedUserProfileEntity);
        }catch (Exception e){
            logger.error("Error while adding profile pic id ", e);
            throw new BusinessException("Error while adding profile pic id");
        }
    }

    @Override
    public ProfileDetails fetchProfileDetails() throws BusinessException{
        UserProfileEntity userProfileEntity = getCurrentUserProfileEntity();
        if(userProfileEntity != null){
            return convertUserProfileEntityToProfileDetails(userProfileEntity);
        } else {
            throw new BusinessException("Profile details are not registered");
        }
    }

    @Override
    public User fetchCurrentUser() throws BusinessException{
        return CommonUtil.getCurrentUser();
    }

    private UserProfileEntity getCurrentUserProfileEntity() throws BusinessException {
        User user = CommonUtil.getCurrentUser();
        UserEntity userEntity = userDao.getUserById(user.getUserId());
        UserProfileEntity userProfileEntity = userProfileDao.getProfileByUser(userEntity);
        return userProfileEntity;
    }

    private UserProfileEntity createOrUpdateUserProfileEntity(ProfileDetails profileDetails, UserProfileEntity userProfileEntity) {
        if(userProfileEntity == null){
            userProfileEntity = createUserProfileEntity(profileDetails);
        }else {
            updateUserProfileEntity(profileDetails, userProfileEntity);
        }
        return userProfileEntity;
    }

    //TODO - Refactor method
    private void updateUserProfileEntity(ProfileDetails profileDetails, UserProfileEntity userProfileEntity) {
        if(profileDetails.getAbout() != null)
            userProfileEntity.setAbout(profileDetails.getAbout());
        if(profileDetails.getLanguages() != null)
            userProfileEntity.setLanguages(profileDetails.getLanguages());
        if(profileDetails.getProfilePic() != null)
            userProfileEntity.setProfilePic(profileDetails.getProfilePic());
        if(profileDetails.getFiles() != null)
            userProfileEntity.setFiles(profileDetails.getFiles());

        Address address = profileDetails.getAddress();
        if(address != null) {
            AddressEntity addressEntity = new AddressEntity(address.getStreet(), address.getCity(), address.getState(),
                    address.getZipCode(), address.getLongitude(), address.getLatitude());
            addressEntity.setAddressId(address.getAddressId());
            addressEntity = addressRepository.save(addressEntity);
            userProfileEntity.setAddressEntity(addressEntity);
        }

        Category category = profileDetails.getCategory();
        if(category != null) {
            CategoryEntity categoryEntity = new CategoryEntity(category.getCategory(),
                    category.getSkills());
            categoryEntity.setCategoryId(category.getCategoryId());
            categoryEntity = categoryRepository.save(categoryEntity);
            userProfileEntity.setCategoryEntity(categoryEntity);
        }

        List<EducationDetails> educationDetails = profileDetails.getEducationDetails();
        if(educationDetails != null) {
            List<EducationDetailsEntity> educationDetailsEntities = new ArrayList<>();
            for (EducationDetails educationDetail : educationDetails) {
                EducationDetailsEntity educationDetailsEntity = new EducationDetailsEntity(educationDetail.getDegree(), educationDetail.getCollege());
                educationDetailsEntity.setUserProfileEntity(userProfileEntity);
                educationDetailsEntity.setEducationDetailsId(educationDetail.getEducationDetailsId());
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
                experienceEntity.setExperienceEntityId(experience.getExperienceEntityId());
                experienceEntities.add(experienceEntity);
            }
            if (!experienceEntities.isEmpty()) {
                userProfileEntity.setExperienceEntities(experienceEntities);
            }
        }
    }

    private UserProfileEntity createUserProfileEntity(ProfileDetails profileDetails){

        UserProfileEntity userProfileEntity = new UserProfileEntity();
        String profileId = UUID.randomUUID().toString();
        userProfileEntity.setProfileId(profileId);

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

    private ProfileDetails convertUserProfileEntityToProfileDetails(UserProfileEntity updatedUserProfileEntity) {
        ProfileDetails profileDetails = new ProfileDetails();
        profileDetails.setAbout(updatedUserProfileEntity.getAbout());
        profileDetails.setProfilePic(updatedUserProfileEntity.getProfilePic());
        profileDetails.setLanguages(updatedUserProfileEntity.getLanguages());
        profileDetails.setFiles(updatedUserProfileEntity.getFiles());

        AddressEntity addressEntity = updatedUserProfileEntity.getAddressEntity();
        if(addressEntity != null)
            profileDetails.setAddress(new Address(addressEntity.getAddressId(), addressEntity.getStreet(), addressEntity.getCity(),
                addressEntity.getState(), addressEntity.getZipCode(), addressEntity.getLongitude(), addressEntity.getLatitude()));

        CategoryEntity categoryEntity = updatedUserProfileEntity.getCategoryEntity();
        if(categoryEntity != null)
            profileDetails.setCategory(new Category(categoryEntity.getCategoryId(), categoryEntity.getCategory(), categoryEntity.getSkills()));

        List<ExperienceEntity> experienceEntities = updatedUserProfileEntity.getExperienceEntities();
        if(experienceEntities !=null){
            List<Experience> experiences = new ArrayList<>();
            for (ExperienceEntity experienceEntity: experienceEntities) {
                experiences.add(new Experience(experienceEntity.getExperienceEntityId(), experienceEntity.getCompanyName(),
                        experienceEntity.getRole(), experienceEntity.getDetails(), experienceEntity.getYears()));
            }
            profileDetails.setExperiences(experiences);
        }

        List<EducationDetailsEntity> educationDetailsEntities = updatedUserProfileEntity.getEducationDetailEntities();
        if(educationDetailsEntities != null){
            List<EducationDetails> educationDetails = new ArrayList<>();
            for (EducationDetailsEntity educationDetailsEntity: educationDetailsEntities) {
                educationDetails.add(new EducationDetails(educationDetailsEntity.getEducationDetailsId(), educationDetailsEntity.getDegree(),
                        educationDetailsEntity.getCollege()));
            }
            profileDetails.setEducationDetails(educationDetails);
        }

        return profileDetails;
    }
}