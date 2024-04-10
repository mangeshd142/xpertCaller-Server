package com.xpertcaller.server.user.db.sql.repositories;

import com.xpertcaller.server.user.db.sql.entities.UserEntity;
import com.xpertcaller.server.user.db.sql.entities.profileEntities.AvailableTimeSlotEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AvailableTimeSlotRepository extends JpaRepository<AvailableTimeSlotEntity, String> {
    @Query("SELECT up FROM AvailableTimeSlotEntity up WHERE up.userEntity = :userEntity")
    List<AvailableTimeSlotEntity> findByUserEntity(@Param("userEntity") UserEntity userEntity);

    List<AvailableTimeSlotEntity> findByUserEntityUserId(String userId);
}
