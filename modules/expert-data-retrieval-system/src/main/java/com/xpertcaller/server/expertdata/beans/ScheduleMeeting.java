package com.xpertcaller.server.expertdata.beans;

import com.xpertcaller.server.user.db.sql.entities.profileEntities.AvailableTimeSlotChunksEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "schedule_meeting")
public class ScheduleMeeting {

    @Id
    private String bookingId;
    private String publisher;
    private String subscriber;
    private int status;
    private List<String> timeSlotIds;
    private String mode;
    @OneToMany
    private List<AvailableTimeSlotChunksEntity> availableTimeSlotChunksEntities;

}
