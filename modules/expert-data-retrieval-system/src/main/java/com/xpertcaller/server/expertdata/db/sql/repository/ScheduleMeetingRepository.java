package com.xpertcaller.server.expertdata.db.sql.repository;

import com.xpertcaller.server.expertdata.db.sql.entities.ScheduleMeetingEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ScheduleMeetingRepository  extends JpaRepository<ScheduleMeetingEntity, String> {
    public List<ScheduleMeetingEntity> findBySubscriber(String subscriber);
    public List<ScheduleMeetingEntity> findByPublisher(String publisher);
}
