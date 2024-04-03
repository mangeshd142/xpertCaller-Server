package com.xpertcaller.server.bo.interfaces;

import com.xpertcaller.server.beans.user.ProfileDetails;
import com.xpertcaller.server.beans.user.User;
import com.xpertcaller.server.exception.userdefined.BusinessException;

import java.util.List;

public interface UserProfileBo {
    ProfileDetails addOrUpdateProfileDetails(ProfileDetails profileDetails) throws BusinessException;

    ProfileDetails updateProfilePictureId(String profileImageId) throws BusinessException;

    ProfileDetails updateDocumentIds(List<String> profilePicIds) throws BusinessException;

    ProfileDetails fetchProfileDetails() throws BusinessException;

    User fetchCurrentUser() throws BusinessException;
}
