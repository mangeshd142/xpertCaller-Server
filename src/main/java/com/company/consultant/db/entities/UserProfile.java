package com.company.consultant.db.entities;

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
public class UserProfile {
    @Id
    private String profileId;
    private String education;
    private String experience;
    private String detailedInfo;
    private String profilePicture;
    private List<String> certificates;
    private String designation;
    private List<String> languages;
    private String location;
    private String address;
    private List<String> reviewRating;
}
