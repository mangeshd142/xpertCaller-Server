package com.xpertcaller.server.moduls.jwt;

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
}
