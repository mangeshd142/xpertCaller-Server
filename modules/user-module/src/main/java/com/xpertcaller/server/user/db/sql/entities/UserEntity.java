package com.xpertcaller.server.user.db.sql.entities;

import com.xpertcaller.server.user.db.sql.entities.profileEntities.AddressEntity;
import com.xpertcaller.server.user.db.sql.entities.profileEntities.UserProfileEntity;
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
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    UserProfileEntity userProfileEntity;

    public UserEntity(String userId, String username, String email, String name, int age, String about, AddressEntity addressEntity, String gender, String profilePic, String password, String mobileNumber, boolean isActive, String role, String otp) {
        this.userId = userId;
        this.username = username;
        this.email = email;
        this.name = name;
        this.age = age;
        this.about = about;
        this.addressEntity = addressEntity;
        this.gender = gender;
        this.profilePic = profilePic;
        this.password = password;
        this.mobileNumber = mobileNumber;
        this.isActive = isActive;
        this.role = role;
        this.otp = otp;
    }
}
