package com.xpertcaller.server.db.interfaces.dao;

import com.xpertcaller.server.db.sql.entities.UserProfileEntity;

public interface UserProfileDao {
    UserProfileEntity getUserProfileByUserId(String userId);

    UserProfileEntity saveUserProfile(UserProfileEntity userProfileEntity);
}
