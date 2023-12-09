package com.company.consultant.moduls;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ConsultationSkills {
    private String consultationSkillId;
    private String consultationSkillName;
    private String consultationSkillDescription;
    private String parentId;
}
