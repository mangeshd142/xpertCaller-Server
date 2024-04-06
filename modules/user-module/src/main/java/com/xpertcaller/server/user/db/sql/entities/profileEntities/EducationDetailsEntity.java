package com.xpertcaller.server.user.db.sql.entities.profileEntities;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "education_details_table")
public class EducationDetailsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String educationDetailsId;
    private String degree;
    private String college;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_profile_id", referencedColumnName = "profileId")
    private UserProfileEntity userProfileEntity;

    public EducationDetailsEntity(String degree, String college) {
        this.degree = degree;
        this.college = college;
    }
}
