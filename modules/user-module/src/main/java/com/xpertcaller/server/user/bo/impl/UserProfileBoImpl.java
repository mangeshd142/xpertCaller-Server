package com.xpertcaller.server.user.bo.impl;

import com.xpertcaller.server.user.beans.user.*;
import com.xpertcaller.server.user.bo.interfaces.UserProfileBo;
import com.xpertcaller.server.user.db.interfaces.dao.EducationDetailsDao;
import com.xpertcaller.server.user.db.interfaces.dao.ExperienceDao;
import com.xpertcaller.server.user.db.interfaces.dao.UserDao;
import com.xpertcaller.server.user.db.interfaces.dao.UserProfileDao;
import com.xpertcaller.server.user.db.sql.entities.UserEntity;
import com.xpertcaller.server.user.db.sql.entities.profileEntities.*;
import com.xpertcaller.server.user.db.sql.repositories.AddressRepository;
import com.xpertcaller.server.user.db.sql.repositories.CategoryRepository;
import com.xpertcaller.server.common.exception.BusinessException;
import com.xpertcaller.server.user.service.UserServiceImpl;
import com.xpertcaller.server.user.util.CommonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class UserProfileBoImpl implements UserProfileBo {

    @Autowired
    UserProfileDao userProfileDao;
    @Autowired
    AddressRepository addressRepository;
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    EducationDetailsDao educationDetailsDao;
    @Autowired
    ExperienceDao experienceDao;
    @Autowired
    UserDao userDao;
    private static final Logger logger = LoggerFactory.getLogger(UserProfileBoImpl.class);

    @Override
    public ProfileDetails addOrUpdateProfileDetails(ProfileDetails profileDetails) throws BusinessException {
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
            logger.debug("Profile details are not available");
            return new ProfileDetails();
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

        if(profileDetails.getLanguages() != null)
            userProfileEntity.setLanguages(profileDetails.getLanguages());
        if(profileDetails.getProfilePic() != null)
            userProfileEntity.setProfilePic(profileDetails.getProfilePic());
        if(profileDetails.getFiles() != null)
            userProfileEntity.setFiles(profileDetails.getFiles());

        Category category = profileDetails.getCategory();
        if(category != null) {
            CategoryEntity categoryEntity = new CategoryEntity(category.getCategory(),
                    category.getSkills());
            categoryEntity.setCategoryId(category.getId());
            categoryEntity = categoryRepository.save(categoryEntity);
            userProfileEntity.setCategoryEntity(categoryEntity);
        }

        List<EducationDetails> educationDetails = profileDetails.getEducationDetails();
        if(educationDetails != null) {
            List<EducationDetailsEntity> educationDetailsEntities = new ArrayList<>();
            for (EducationDetails educationDetail : educationDetails) {
                EducationDetailsEntity educationDetailsEntity = new EducationDetailsEntity(educationDetail.getDegree(), educationDetail.getCollege());
                educationDetailsEntity.setUserProfileEntity(userProfileEntity);
                educationDetailsEntity.setEducationDetailsId(educationDetail.getId());
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
                experienceEntity.setExperienceEntityId(experience.getId());
                experienceEntities.add(experienceEntity);
            }
            if (!experienceEntities.isEmpty()) {
                userProfileEntity.setExperienceEntities(experienceEntities);
            }
        }

        List<String> deleteEducationDetailIds = profileDetails.getDeleteEducationDetailIds();
        if(deleteEducationDetailIds != null)
            deleteEducationDetailIds.forEach( id ->{
                userProfileEntity.setEducationDetailEntities(deleteEducationDetail(userProfileEntity.getEducationDetailEntities(), id));
            });

        List<String> deleteExperienceIds = profileDetails.getDeleteExperienceIds();
        if (deleteExperienceIds != null)
            deleteExperienceIds.forEach(id->{
                userProfileEntity.setExperienceEntities(deleteExperience(userProfileEntity.getExperienceEntities(), id));
            });

    }

    private List<EducationDetailsEntity> deleteEducationDetail(List<EducationDetailsEntity> list, String eduId) {
        educationDetailsDao.deleteEducationDetails(eduId);
        return list.stream()
                .filter(obj -> obj.getEducationDetailsId() == null || !obj.getEducationDetailsId().equals(eduId))
                .collect(Collectors.toList());
    }

    private List<ExperienceEntity> deleteExperience(List<ExperienceEntity> list, String experienceId) {
        experienceDao.deleteExperience(experienceId);
        return list.stream()
                .filter(obj -> obj.getExperienceEntityId() == null || !obj.getExperienceEntityId().equals(experienceId))
                .collect(Collectors.toList());
    }

    private UserProfileEntity createUserProfileEntity(ProfileDetails profileDetails){

        UserProfileEntity userProfileEntity = new UserProfileEntity();
        String profileId = UUID.randomUUID().toString();
        userProfileEntity.setProfileId(profileId);

        userProfileEntity.setLanguages(profileDetails.getLanguages());
        userProfileEntity.setProfilePic(profileDetails.getProfilePic());
        userProfileEntity.setFiles(profileDetails.getFiles());

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
        profileDetails.setProfilePic(updatedUserProfileEntity.getProfilePic());
        profileDetails.setLanguages(updatedUserProfileEntity.getLanguages());
        profileDetails.setFiles(updatedUserProfileEntity.getFiles());

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
