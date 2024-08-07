package com.xpertcaller.server.user.beans;

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
