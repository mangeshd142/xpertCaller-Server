package com.xpertcaller.server.user.db.interfaces.dao;

import com.xpertcaller.server.user.db.sql.entities.profileEntities.ScheduleMeetingEntity;

import java.util.Date;
import java.util.List;

public interface ScheduleMeetingDao {
    ScheduleMeetingEntity saveScheduleMeeting(ScheduleMeetingEntity scheduleMeetingEntity);

    List<ScheduleMeetingEntity> saveAllScheduleMeeting(List<ScheduleMeetingEntity> scheduleMeetingEntities);

    ScheduleMeetingEntity getScheduleMeetingById(String scheduleMeetingId);

    List<ScheduleMeetingEntity> getAllScheduleMeetingsBySubscriber(String subscriber);

    List<ScheduleMeetingEntity> getAllScheduleMeetingsByPublisher(String publisher);

    List<ScheduleMeetingEntity> getAllScheduleMeetingsBySubscriberOrPublisher(String subscriber, String publisher);

    List<ScheduleMeetingEntity> getAllScheduleMeetingsBySubscriberOrPublisherAndStartTimeBetween(String subscriber, String publisher, Date startTime, Date endTime);
}
