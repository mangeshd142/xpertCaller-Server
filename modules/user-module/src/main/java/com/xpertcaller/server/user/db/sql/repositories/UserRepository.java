package com.xpertcaller.server.user.db.sql.repositories;

import com.xpertcaller.server.user.db.sql.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, String> {
        public Optional<UserEntity> findByUsername(String userName);
        public UserEntity findByMobileNumber(String mobileNumber);
        //@Query("SELECT u FROM UserEntity u JOIN u.userProfileEntity up JOIN up.categoryEntity c WHERE :category = '' OR :category MEMBER OF c.category")
        @Query("SELECT u FROM UserEntity u JOIN u.userProfileEntity up JOIN up.categoryEntity c WHERE (:category = '' OR :category MEMBER OF c.category) AND (:gender = '' OR u.gender = :gender)")
        List<UserEntity> findByCategory(@Param("category") String category, @Param("gender") String gender);

}
