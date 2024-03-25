package com.xpertcaller.server.beans.jwt;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class JwtResponse {
    String username;
    String jwtToken;
    String name;
}
