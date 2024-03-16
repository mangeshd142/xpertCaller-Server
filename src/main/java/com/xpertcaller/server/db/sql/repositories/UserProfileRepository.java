package com.xpertcaller.server.db.sql.repositories;


import com.xpertcaller.server.db.sql.entities.UserProfileEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserProfileRepository extends JpaRepository<UserProfileEntity, String>{
    public UserProfileEntity findByUserId(String userId);
}
