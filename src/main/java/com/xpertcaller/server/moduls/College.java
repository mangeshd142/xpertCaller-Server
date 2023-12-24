package com.xpertcaller.server.moduls;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class College {
    private String collegeId;
    private String university;
    private String collegeName;
    private String collegeType;
    private String state;
    private String district;
}
