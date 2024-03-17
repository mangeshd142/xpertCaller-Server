package com.xpertcaller.server.service.interfaces;

import com.xpertcaller.server.exception.userdefined.BusinessException;
import com.xpertcaller.server.moduls.user.AddCategory;
import com.xpertcaller.server.moduls.user.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

public interface UserService {
    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
    User createUser(User user);

    User getUserByMobileNumber(String mobileNumber);
    User sendOtp(String mobileNumber) throws BusinessException;
    List<User> getAllUsers();

    void deleteOtp(String mobileNumber);

    User addCategory(AddCategory addCategory) throws BusinessException;
}
