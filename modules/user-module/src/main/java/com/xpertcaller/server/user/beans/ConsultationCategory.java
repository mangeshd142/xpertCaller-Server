package com.xpertcaller.server.user.beans;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ConsultationCategory {
    private String consultationId;
    private String consultationName;
    private String consultationDescription;
    private String image;
}
