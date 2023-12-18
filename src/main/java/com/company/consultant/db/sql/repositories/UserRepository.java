package com.company.consultant.db.sql.repositories;

import com.company.consultant.db.sql.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, String> {
        public Optional<UserEntity> findByUsername(String userName);
        public UserEntity findByMobileNumber(String mobileNumber);
}