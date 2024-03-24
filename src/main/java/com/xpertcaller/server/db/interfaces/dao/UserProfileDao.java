package com.xpertcaller.server.db.interfaces.dao;

import com.xpertcaller.server.db.sql.entities.UserEntity;
import com.xpertcaller.server.db.sql.entities.profileEntities.UserProfileEntity;

public interface UserProfileDao {
    UserProfileEntity saveUserProfile(UserProfileEntity userProfileEntity);

    UserProfileEntity getProfileByUser(UserEntity userEntity);
}
