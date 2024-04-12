package com.xpertcaller.server.user.beans.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class User implements UserDetails, Serializable {
    private static final long serialVersionUID = 1L;
    private String userId;
    private String username;
    private String email;
    private String name;
    private int age;
    private String about;
    private Address address;
    private String gender;
    private String profilePic;
    @JsonIgnore
    private String password;
    private String mobileNumber;
    private boolean isActive;
    private String role;
    @JsonIgnore
    private String otp;
    private boolean isPasswordAuthentication;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.emptyList();
    }

    @Override
    public String getUsername() {
        return this.mobileNumber;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return isActive;
    }

    @Override
    public String getPassword(){
        return this.otp;
    }
}
