package com.xpertcaller.server.expertdata.beans;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AvailableTimeSlotRequest {
    private String userId;
    private String zone;
    private long date;
}
