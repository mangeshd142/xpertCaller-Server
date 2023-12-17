package com.company.consultant.db.dao.interfaces;

import com.company.consultant.db.entities.UserEntity;

import java.util.List;
import java.util.Optional;

public interface UserDao {
    Optional<UserEntity> findByUsername(String userName);

    UserEntity findByMobileNumber(String mobileNumber);
    List<UserEntity> getAllUsers();

    UserEntity saveUser(UserEntity userEntity);
}
