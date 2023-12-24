package com.xpertcaller.server.db.sql.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
    private String parentId;
}
