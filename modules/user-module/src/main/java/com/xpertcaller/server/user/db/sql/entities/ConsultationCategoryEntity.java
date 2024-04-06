package com.xpertcaller.server.user.db.sql.entities;

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
@Entity
@Table(name = "consultation_category_table")
public class ConsultationCategoryEntity {
    @Id
    private String consultationId;
    @Column(unique = true)
    private String consultationName;
    private String consultationDescription;
    private String image;
}
