package com.company.consultant.moduls;

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
