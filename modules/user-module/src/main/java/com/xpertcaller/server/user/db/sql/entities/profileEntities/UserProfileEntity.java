package com.xpertcaller.server.user.db.sql.entities.profileEntities;

import com.xpertcaller.server.user.db.sql.entities.UserEntity;
import jakarta.persistence.*;
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
    @OneToOne
    private UserEntity userEntity;
    private String profilePic;
    private List<String> languages;
    @OneToMany(mappedBy = "userProfileEntity", cascade = CascadeType.ALL)
    private List<EducationDetailsEntity> educationDetailEntities;
    @OneToOne(cascade = CascadeType.ALL)
    private CategoryEntity categoryEntity;
    @OneToMany(mappedBy = "userProfileEntity", cascade = CascadeType.ALL)
    private List<ExperienceEntity> experienceEntities;
    private List<String> files;
}