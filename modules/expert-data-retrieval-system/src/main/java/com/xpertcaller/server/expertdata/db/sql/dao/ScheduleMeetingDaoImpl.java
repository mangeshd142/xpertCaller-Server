package com.xpertcaller.server.expertdata.db.sql.dao;

import com.xpertcaller.server.expertdata.db.interfaces.ScheduleMeetingDao;
import com.xpertcaller.server.expertdata.db.sql.entities.ScheduleMeetingEntity;
import com.xpertcaller.server.expertdata.db.sql.repository.ScheduleMeetingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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

}
