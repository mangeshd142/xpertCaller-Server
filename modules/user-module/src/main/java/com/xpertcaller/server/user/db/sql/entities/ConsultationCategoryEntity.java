package com.xpertcaller.server.user.db.sql.entities;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "consultation_category_table")
public class ConsultationCategoryEntity {
    @Id
    private String consultationId;
    @Column(unique = true)
    private String consultationName;
    private String consultationDescription;
    private String image;
    @OneToMany(mappedBy = "consultationCategoryEntity", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<ConsultationSkillsEntity> consultationSkills;
}
