package com.xpertcaller.server.user.db.sql.dao;

import com.xpertcaller.server.user.db.interfaces.dao.AvailableTimeSlotDao;
import com.xpertcaller.server.user.db.sql.entities.profileEntities.AvailableTimeSlotChunksEntity;
import com.xpertcaller.server.user.db.sql.entities.profileEntities.AvailableTimeSlotEntity;
import com.xpertcaller.server.user.db.sql.repositories.AvailableTimeSlotChunksRepository;
import com.xpertcaller.server.user.db.sql.repositories.AvailableTimeSlotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Component
public class AvailableTimeSlotDaoImpl implements AvailableTimeSlotDao {
    @Autowired
    AvailableTimeSlotRepository availableTimeSlotRepository;
    @Autowired
    AvailableTimeSlotChunksRepository availableTimeSlotChunksRepository;

    @Override
    public AvailableTimeSlotEntity addSchedule(AvailableTimeSlotEntity availableTimeSlotEntity){
        return availableTimeSlotRepository.save(availableTimeSlotEntity);
    }

    @Override
    public List<AvailableTimeSlotEntity> addAllSchedule(List<AvailableTimeSlotEntity> availableTimeSlotEntityList){
        return availableTimeSlotRepository.saveAll(availableTimeSlotEntityList);
    }

    @Override
    public List<AvailableTimeSlotEntity> getAllSchedule(){
        return availableTimeSlotRepository.findAll();
    }

    @Override
    public Optional<AvailableTimeSlotEntity> getSchedule(String id){
        return availableTimeSlotRepository.findById(id);
    }

    @Override
    public List<AvailableTimeSlotEntity> getAllTimeSlotsOfCurrentUser(String userId){
        return availableTimeSlotRepository.findByUserEntityUserId(userId);
    }
    
    @Override
    public List<AvailableTimeSlotEntity> getTimeSlotsOfStartDateInBetween(String userId, Date startDate, Date endDate){
        return availableTimeSlotRepository.findByUserEntityUserIdAndStartTimeBetween(userId, startDate, endDate);
    }

    @Override
    public List<AvailableTimeSlotChunksEntity> addOrUpdateAvailableTimeslotChunks(List<AvailableTimeSlotChunksEntity> availableTimeSlotChunksEntities){
        return availableTimeSlotChunksRepository.saveAll(availableTimeSlotChunksEntities);
    }

    @Override
    public AvailableTimeSlotChunksEntity addOrUpdateAvailableTimeslotChunk(AvailableTimeSlotChunksEntity availableTimeSlotChunksEntity){
        return availableTimeSlotChunksRepository.save(availableTimeSlotChunksEntity);
    }

    @Override
    public List<AvailableTimeSlotChunksEntity> getAvailableTimeslotChunksByAvailableTimeSlot(String timeSlotId){
        return availableTimeSlotChunksRepository.findByAvailableTimeSlotEntityTimeSlotId(timeSlotId);
    }

}
