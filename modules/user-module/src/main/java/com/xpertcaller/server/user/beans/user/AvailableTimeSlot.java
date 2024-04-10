package com.xpertcaller.server.user.beans.user;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AvailableTimeSlot {
    private String id;
    private long startTime;
    private long endTime;
    private String zone;
}
