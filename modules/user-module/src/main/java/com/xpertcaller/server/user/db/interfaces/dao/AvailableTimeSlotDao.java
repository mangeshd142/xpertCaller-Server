package com.xpertcaller.server.user.db.interfaces.dao;

import com.xpertcaller.server.user.db.sql.entities.profileEntities.AvailableTimeSlotChunksEntity;
import com.xpertcaller.server.user.db.sql.entities.profileEntities.AvailableTimeSlotEntity;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface AvailableTimeSlotDao {
    AvailableTimeSlotEntity addSchedule(AvailableTimeSlotEntity availableTimeSlotEntity);

    List<AvailableTimeSlotEntity> addAllSchedule(List<AvailableTimeSlotEntity> availableTimeSlotEntityList);

    List<AvailableTimeSlotEntity> getAllSchedule();

    Optional<AvailableTimeSlotEntity> getSchedule(String id);

    List<AvailableTimeSlotEntity> getAllTimeSlotsOfCurrentUser(String userId);

    List<AvailableTimeSlotEntity> getTimeSlotsOfStartDateInBetween(String userId, Date startDate, Date endDate);

    List<AvailableTimeSlotChunksEntity> addOrUpdateAvailableTimeslotChunks(List<AvailableTimeSlotChunksEntity> availableTimeSlotChunksEntities);

    AvailableTimeSlotChunksEntity addOrUpdateAvailableTimeslotChunk(AvailableTimeSlotChunksEntity availableTimeSlotChunksEntity);

    AvailableTimeSlotChunksEntity getAvailableTimeSlotChunksEntityById(String id);

    List<AvailableTimeSlotChunksEntity> getAvailableTimeslotChunksByAvailableTimeSlot(String timeSlotId);
}
