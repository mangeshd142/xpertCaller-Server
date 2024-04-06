package com.xpertcaller.server.user.db.sql.dao;

import com.xpertcaller.server.user.db.interfaces.dao.UserProfileDao;
import com.xpertcaller.server.user.db.sql.entities.UserEntity;
import com.xpertcaller.server.user.db.sql.entities.profileEntities.UserProfileEntity;
import com.xpertcaller.server.user.db.sql.repositories.UserProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserProfileDaoImpl implements UserProfileDao {
    @Autowired
    private UserProfileRepository userProfileRepository;

    @Override
    public UserProfileEntity saveUserProfile(UserProfileEntity userProfileEntity){
        return userProfileRepository.save(userProfileEntity);
    }
    @Override
    public UserProfileEntity getProfileByUser(UserEntity userEntity){
        return userProfileRepository.findByUserEntity(userEntity);
    }
}
