package com.xpertcaller.server.expertdata.beans;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ScheduleMeetingFilter {
    private long date;
    private String mode;
    private int status;
}
