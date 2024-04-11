package com.xpertcaller.server.user.beans.user;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AvailableTimeSlotRequest {
    String zone;
    long date;
}
