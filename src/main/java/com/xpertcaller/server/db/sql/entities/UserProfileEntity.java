package com.xpertcaller.server.db.sql.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "user_profile_table")
public class UserProfileEntity {
    @Id
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
