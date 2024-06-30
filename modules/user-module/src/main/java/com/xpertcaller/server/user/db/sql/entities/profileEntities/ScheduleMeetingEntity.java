package com.xpertcaller.server.user.db.sql.entities.profileEntities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "schedule_meeting")
public class ScheduleMeetingEntity {

    @Id
    private String bookingId;
    private String publisher;
    private String subscriber;
    private int status;
    private String mode;
    private Date  startTime;
    private Date endTime;
    @OneToMany(mappedBy = "scheduleMeetingEntity", cascade = CascadeType.ALL)
    private List<AvailableTimeSlotChunksEntity> availableTimeSlotChunksEntities;

}
