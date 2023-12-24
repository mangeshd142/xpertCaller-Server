package com.company.consultant.db.interfaces.dao;

import com.company.consultant.db.sql.entities.UserEntity;

import java.util.List;
import java.util.Optional;

public interface UserDao {
    Optional<UserEntity> findByUsername(String userName);

    UserEntity findByMobileNumber(String mobileNumber);
    List<UserEntity> getAllUsers();

    UserEntity saveUser(UserEntity userEntity);

    UserEntity getUserById(String id);
}
