package com.xpertcaller.server.beans.jwt;

import com.xpertcaller.server.beans.user.User;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class JwtResponse {
    String jwtToken;
    User user;
}
