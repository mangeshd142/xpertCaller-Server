package com.xpertcaller.server.user.beans.jwt;

import com.xpertcaller.server.user.beans.user.User;
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
