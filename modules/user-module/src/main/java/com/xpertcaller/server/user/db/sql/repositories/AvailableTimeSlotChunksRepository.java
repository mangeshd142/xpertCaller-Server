package com.xpertcaller.server.user.db.sql.repositories;

import com.xpertcaller.server.user.db.sql.entities.UserEntity;
import com.xpertcaller.server.user.db.sql.entities.profileEntities.AvailableTimeSlotChunksEntity;
import com.xpertcaller.server.user.db.sql.entities.profileEntities.AvailableTimeSlotEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AvailableTimeSlotChunksRepository  extends JpaRepository<AvailableTimeSlotChunksEntity, String> {

    @Query("SELECT up FROM AvailableTimeSlotChunksEntity up WHERE up.availableTimeSlotEntity = :availableTimeSlotEntity")
    List<AvailableTimeSlotChunksEntity> findByAvailableTimeSlotEntity(@Param("availableTimeSlotEntity") AvailableTimeSlotEntity availableTimeSlotEntity);

    List<AvailableTimeSlotChunksEntity> findByAvailableTimeSlotEntityTimeSlotId(String id);


}
