package com.company.consultant.service.interfaces;

import com.company.consultant.db.entities.User;

import java.util.List;

public interface IUserService {
    User createUser(User user);
    User getUserByMobileNumber(String mobileNumber);
    User sendOtp(String mobileNumber);
    public List<User> getAllUsers();
}
