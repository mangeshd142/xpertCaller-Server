package com.xpertcaller.server.user.db.interfaces.dao;

import com.xpertcaller.server.user.db.sql.entities.profileEntities.ScheduleMeetingEntity;

import java.util.List;

public interface ScheduleMeetingDao {
    ScheduleMeetingEntity saveScheduleMeeting(ScheduleMeetingEntity scheduleMeetingEntity);

    List<ScheduleMeetingEntity> saveAllScheduleMeeting(List<ScheduleMeetingEntity> scheduleMeetingEntities);

    ScheduleMeetingEntity getScheduleMeetingById(String scheduleMeetingId);

    List<ScheduleMeetingEntity> getAllScheduleMeetingsBySubscriber(String subscriber);

    List<ScheduleMeetingEntity> getAllScheduleMeetingsByPublisher(String publisher);
}
