package com.xpertcaller.server.user.db.interfaces.dao;

import com.xpertcaller.server.user.db.sql.entities.UserEntity;
import com.xpertcaller.server.user.db.sql.entities.profileEntities.PricingEntity;
import com.xpertcaller.server.user.db.sql.entities.profileEntities.UserProfileEntity;

public interface UserProfileDao {
    UserProfileEntity saveUserProfile(UserProfileEntity userProfileEntity);

    UserProfileEntity getProfileByUser(UserEntity userEntity);

    PricingEntity savePricing(PricingEntity pricingEntity);
}
