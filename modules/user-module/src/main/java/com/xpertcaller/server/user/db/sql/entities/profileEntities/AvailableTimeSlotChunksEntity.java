package com.xpertcaller.server.user.db.sql.entities.profileEntities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "available_timeSlot_chunks_table")
public class AvailableTimeSlotChunksEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String timeSlotChunkId;
    private Date startTime;
    private Date endTime;
    private String zone;
    private String status;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "timeSlot_id", referencedColumnName = "timeSlotId")
    private AvailableTimeSlotEntity availableTimeSlotEntity;

    public AvailableTimeSlotChunksEntity(String timeSlotChunkId, Date startTime, Date endTime, String zone, String status){
        this.timeSlotChunkId = timeSlotChunkId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.zone = zone;
        this.status = status;
    }

}
