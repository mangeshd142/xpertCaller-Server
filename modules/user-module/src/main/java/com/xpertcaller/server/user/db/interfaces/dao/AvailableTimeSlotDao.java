package com.xpertcaller.server.user.db.interfaces.dao;

import com.xpertcaller.server.user.db.sql.entities.UserEntity;
import com.xpertcaller.server.user.db.sql.entities.profileEntities.AvailableTimeSlotEntity;

import java.util.List;
import java.util.Optional;

public interface AvailableTimeSlotDao {
    AvailableTimeSlotEntity addSchedule(AvailableTimeSlotEntity availableTimeSlotEntity);

    List<AvailableTimeSlotEntity> addAllSchedule(List<AvailableTimeSlotEntity> availableTimeSlotEntityList);

    List<AvailableTimeSlotEntity> getAllSchedule();

    Optional<AvailableTimeSlotEntity> getSchedule(String id);

    List<AvailableTimeSlotEntity> getAllTimeSlotsOfCurrentUser(String userId);
}