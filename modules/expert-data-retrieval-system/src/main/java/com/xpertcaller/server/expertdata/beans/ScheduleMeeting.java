package com.xpertcaller.server.expertdata.beans;

import com.xpertcaller.server.user.db.sql.entities.profileEntities.AvailableTimeSlotChunksEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ScheduleMeeting {

    private String bookingId;
    private int duration;
    private String publisher;
    private String subscriber;
    private int status;
    private String timeSlotId;
    private String mode;
}
