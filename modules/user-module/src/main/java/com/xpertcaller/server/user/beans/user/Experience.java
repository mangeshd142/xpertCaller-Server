package com.xpertcaller.server.user.beans.user;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Experience {
    private String id;
    private String companyName;
    private String role;
    private String details;
    private int years;
    private int months;
}
