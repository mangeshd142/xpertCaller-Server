package com.xpertcaller.server.user.db.sql.dao;

import com.xpertcaller.server.user.db.interfaces.dao.ScheduleMeetingDao;
import com.xpertcaller.server.user.db.sql.entities.profileEntities.ScheduleMeetingEntity;
import com.xpertcaller.server.user.db.sql.repositories.ScheduleMeetingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public class ScheduleMeetingDaoImpl implements ScheduleMeetingDao {

    @Autowired
    ScheduleMeetingRepository scheduleMeetingRepository;

    @Override
    public ScheduleMeetingEntity saveScheduleMeeting(ScheduleMeetingEntity scheduleMeetingEntity){
        return scheduleMeetingRepository.save(scheduleMeetingEntity);
    }

    @Override
    public List<ScheduleMeetingEntity> saveAllScheduleMeeting(List<ScheduleMeetingEntity> scheduleMeetingEntities){
         return scheduleMeetingRepository.saveAll(scheduleMeetingEntities);
    }

    @Override
    public ScheduleMeetingEntity getScheduleMeetingById(String scheduleMeetingId){
        return scheduleMeetingRepository.getReferenceById(scheduleMeetingId);
    }

    @Override
    public List<ScheduleMeetingEntity> getAllScheduleMeetingsBySubscriber(String subscriber){
        return scheduleMeetingRepository.findBySubscriber(subscriber);
    }

    @Override
    public List<ScheduleMeetingEntity> getAllScheduleMeetingsByPublisher(String publisher){
        return scheduleMeetingRepository.findByPublisher(publisher);
    }

    @Override
    public List<ScheduleMeetingEntity> getAllScheduleMeetingsBySubscriberOrPublisher(String subscriber, String publisher){
        return scheduleMeetingRepository.findBySubscriberOrPublisher(subscriber, publisher);
    }

    @Override
    public List<ScheduleMeetingEntity> getAllScheduleMeetingsBySubscriberOrPublisherAndStartTimeBetween(String subscriber, String publisher, Date startTime, Date endTime){
        return scheduleMeetingRepository.findByStartTimeBetweenAndSubscriberOrPublisher(startTime, endTime, subscriber, publisher);
    }

}
