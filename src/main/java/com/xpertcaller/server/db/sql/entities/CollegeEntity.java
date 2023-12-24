package com.xpertcaller.server.db.sql.entities;

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
@Table(name = "college_list")
public class CollegeEntity {
    @Id
    private String collegeId;
    @Column(length = 600)
    private String university;
    @Column(length = 600)
    private String college;
    private String collegeType;
    private String state;
    private String district;
}
