package com.xpertcaller.server.user.db.sql.repositories;

import com.xpertcaller.server.user.db.sql.entities.profileEntities.ScheduleMeetingEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ScheduleMeetingRepository  extends JpaRepository<ScheduleMeetingEntity, String> {
    public List<ScheduleMeetingEntity> findBySubscriber(String subscriber);
    public List<ScheduleMeetingEntity> findByPublisher(String publisher);
    public List<ScheduleMeetingEntity> findBySubscriberOrPublisher(String subscriber, String publisher);
}
