package com.xpertcaller.server.beans.user;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Experience {
    private String companyName;
    private String role;
    private String details;
    private String years;
}
