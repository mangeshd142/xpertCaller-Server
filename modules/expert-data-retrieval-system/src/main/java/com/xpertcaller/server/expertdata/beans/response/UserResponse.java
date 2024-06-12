package com.xpertcaller.server.expertdata.beans.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserResponse {
    String userId;
    String name;
    String email;
    String phone;
    String image;
}
