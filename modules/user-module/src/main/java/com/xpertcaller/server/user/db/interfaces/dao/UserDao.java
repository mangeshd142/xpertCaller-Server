package com.xpertcaller.server.user.db.interfaces.dao;

import com.xpertcaller.server.user.db.sql.entities.UserEntity;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface UserDao {
    Optional<UserEntity> findByUsername(String userName);

    UserEntity findByMobileNumber(String mobileNumber);
    List<UserEntity> getAllUsers();

    UserEntity saveUser(UserEntity userEntity);

    List<UserEntity> saveUsers(List<UserEntity> userEntityList);

    UserEntity getUserById(String id);

    List<UserEntity> getUserByFilter(String category, String gender, Pageable pageable);
}
