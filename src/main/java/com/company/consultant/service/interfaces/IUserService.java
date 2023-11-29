package com.company.consultant.service.interfaces;

import com.company.consultant.db.entities.User;

public interface IUserService {
    User createUser(User user);
    User getUserByMobileNumber(String mobileNumber);
    User sendOtp(String mobileNumber);
}
