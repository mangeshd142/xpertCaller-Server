package com.xpertcaller.server.user.beans.user;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AvailableTimeSlotChunks {
    private String timeSlotChunkId;
    private Date startTime;
    private Date endTime;
    private String zone;
}
