package com.xpertcaller.server.expertdata.db.interfaces;

import com.xpertcaller.server.expertdata.db.sql.entities.ScheduleMeetingEntity;

import java.util.List;

public interface ScheduleMeetingDao {
    ScheduleMeetingEntity saveScheduleMeeting(ScheduleMeetingEntity scheduleMeetingEntity);

    List<ScheduleMeetingEntity> saveAllScheduleMeeting(List<ScheduleMeetingEntity> scheduleMeetingEntities);

    ScheduleMeetingEntity getScheduleMeetingById(String scheduleMeetingId);

    List<ScheduleMeetingEntity> getAllScheduleMeetingsBySubscriber(String subscriber);

    List<ScheduleMeetingEntity> getAllScheduleMeetingsByPublisher(String publisher);
}
