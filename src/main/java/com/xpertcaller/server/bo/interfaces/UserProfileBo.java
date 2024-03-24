package com.xpertcaller.server.bo.interfaces;

import com.xpertcaller.server.beans.user.ProfileDetails;
import com.xpertcaller.server.exception.userdefined.BusinessException;

public interface UserProfileBo {
    ProfileDetails addProfileDetails(ProfileDetails profileDetails) throws BusinessException;
}
