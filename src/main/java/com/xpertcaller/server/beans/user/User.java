package com.xpertcaller.server.beans.user;

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
    private String password;
    private String mobileNumber;
    private boolean isActive;
    private String category;
    private String role;
    private String otp;
    private boolean isPasswordAuthentication;
    private transient UserProfile userProfile;

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
