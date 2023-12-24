package com.company.consultant.db.sql.entities;

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
@Table(name = "user_table")
public class UserEntity {
    @Id
    private String userId;
    @Column(unique = true)
    private String username;
    @Column(unique = true)
    private String email;
    private String name;
    private int age;
    private String password;
    @Column(unique = true)
    private String mobileNumber;
    private boolean isActive;
    private String category;
    private String profileId;
    private String role;
    private String otp;
}
