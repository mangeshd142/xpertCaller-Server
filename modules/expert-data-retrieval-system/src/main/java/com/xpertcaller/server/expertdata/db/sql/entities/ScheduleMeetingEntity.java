package com.xpertcaller.server.expertdata.db.sql.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
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
public class ScheduleMeetingEntity {

    @Id
    private String bookingId;
    private String publisher;
    private String subscriber;
    private int status;
    private List<String> timeSlotIds;
    private String mode;

}
