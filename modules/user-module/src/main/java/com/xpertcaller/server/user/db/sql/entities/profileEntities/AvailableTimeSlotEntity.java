package com.xpertcaller.server.user.db.sql.entities.profileEntities;

import com.xpertcaller.server.user.db.sql.entities.UserEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "available_timeSlot_table")
public class AvailableTimeSlotEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String timeSlotId;
    private Date startTime;
    private Date endTime;
    private String zone;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "userId")
    private UserEntity userEntity;

    public AvailableTimeSlotEntity(String timeSlotId, Date startTime, Date endTime, String zone){
        this.timeSlotId = timeSlotId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.zone = zone;
    }
}
