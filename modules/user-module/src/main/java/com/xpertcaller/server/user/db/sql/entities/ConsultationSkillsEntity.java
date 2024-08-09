package com.xpertcaller.server.user.db.sql.entities;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "consultation_skills_table")
public class ConsultationSkillsEntity {
    @Id
    private String consultationSkillId;
    private String consultationSkillName;
    private String consultationSkillDescription;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "consultation_category_id", referencedColumnName = "consultationId")
    private ConsultationCategoryEntity consultationCategoryEntity;

    public ConsultationSkillsEntity(String consultationSkillId, String consultationSkillName, String consultationSkillDescription) {
        this.consultationSkillId = consultationSkillId;
        this.consultationSkillName = consultationSkillName;
        this.consultationSkillDescription = consultationSkillDescription;
    }

}
