package com.xpertcaller.server.user.db.sql.entities.profileEntities;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "experience_table")
public class ExperienceEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String experienceEntityId;
    private String companyName;
    private String role;
    private String details;
    private int years;
    private int months;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_profile_id", referencedColumnName = "profileId")
    private UserProfileEntity userProfileEntity;

    public ExperienceEntity(String companyName, String role, String details, int years, int months) {
        this.companyName = companyName;
        this.role = role;
        this.details = details;
        this.years = years;
        this.months = months;
    }
}
