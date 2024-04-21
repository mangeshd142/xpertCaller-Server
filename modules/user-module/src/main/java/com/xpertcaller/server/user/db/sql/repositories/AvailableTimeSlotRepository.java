package com.xpertcaller.server.user.db.sql.repositories;

import com.xpertcaller.server.user.db.sql.entities.profileEntities.AvailableTimeSlotEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface AvailableTimeSlotRepository extends JpaRepository<AvailableTimeSlotEntity, String> {
    List<AvailableTimeSlotEntity> findByUserEntityUserId(String userId);
    List<AvailableTimeSlotEntity> findByUserEntityUserIdAndStartTimeBetween(String userId, Date startDate, Date currentDate);

}
