package com.xpertcaller.server.user.beans.jwt;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class JwtRequestMobile {
    private String mobileNumber;
    private String password;
}
