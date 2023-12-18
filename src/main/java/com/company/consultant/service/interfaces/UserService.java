package com.company.consultant.service.interfaces;

import com.company.consultant.moduls.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

public interface UserService {
    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
    User createUser(User user);

    User getUserByMobileNumber(String mobileNumber);
    User sendOtp(String mobileNumber);
    List<User> getAllUsers();

    void deleteOtp(String mobileNumber);
}
