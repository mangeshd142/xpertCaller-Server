package com.xpertcaller.server.expertdata.beans.response;

import com.xpertcaller.server.user.beans.user.AvailableTimeSlotChunks;
import jakarta.persistence.Id;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ScheduleMeetingResponse {
    private String bookingId;
    private String publisher;
    private String subscriber;
    private int status;
    private List<String> timeSlotIds;
    private String mode;
    private List<AvailableTimeSlotChunks> timeSlots;
}
