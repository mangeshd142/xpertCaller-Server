package com.xpertcaller.server.user.beans.user;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserProfileRequest {
    private User user;
    private ProfileDetails profileDetails;
}
