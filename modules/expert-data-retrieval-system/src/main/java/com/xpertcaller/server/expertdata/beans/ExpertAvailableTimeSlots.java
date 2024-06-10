package com.xpertcaller.server.expertdata.beans;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ExpertAvailableTimeSlots {
    private String id;
    private long startTime;
    private long endTime;
    private String zone;
    private int status;
}
