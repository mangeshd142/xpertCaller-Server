package com.xpertcaller.server.user.bo.interfaces;

import com.xpertcaller.server.common.exception.BusinessException;
import com.xpertcaller.server.user.beans.user.AvailableTimeSlotRequest;
import com.xpertcaller.server.user.beans.user.ProfileDetails;
import com.xpertcaller.server.user.beans.user.User;
import com.xpertcaller.server.user.db.sql.entities.profileEntities.EducationDetailsEntity;

import java.util.List;

public interface UserProfileBo {
    ProfileDetails addOrUpdateProfileDetails(ProfileDetails profileDetails) throws BusinessException;

    ProfileDetails updateProfilePictureId(String profileImageId) throws BusinessException;

    ProfileDetails updateDocumentIds(List<String> profilePicIds) throws BusinessException;

    ProfileDetails fetchProfileDetails() throws BusinessException;

    User fetchCurrentUser() throws BusinessException;
}
