package com.xpertcaller.server.moduls.user;

import lombok.*;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserProfile implements Serializable {
    private String profileId;
    private String userId;
    private String expertCategory;
    private List<String> skills;
    private String experience;
    private String college;
    private String degree;
    private String detailedInfo;
    private String profilePicture;
    private List<String> certificates;
    private String designation;
    private List<String> languages;
    private String longitude;
    private String latitude;
    private String address;
    private float averageRating;
}
