package com.xpertcaller.server.user.db.sql.entities;

import com.xpertcaller.server.user.db.sql.entities.profileEntities.AddressEntity;
import jakarta.persistence.*;
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
    private String about;
    @OneToOne(cascade = CascadeType.ALL)
    private AddressEntity addressEntity;
    private String gender;
    private String profilePic;
    private String password;
    @Column(unique = true)
    private String mobileNumber;
    private boolean isActive;
    private String role;
    private String otp;
}
