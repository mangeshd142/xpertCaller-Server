package com.company.consultant.service;

import com.company.consultant.db.entities.User;
import com.company.consultant.db.repositories.UserRepository;
import com.company.consultant.service.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;
import java.util.UUID;

@Service
public class UserService implements IUserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    @Override
    public User createUser(User user){

        user.setUserId(UUID.randomUUID().toString());
        user.setActive(true);
        if(user !=null){
            Random random = new Random();
            String randomNumber = "" + random.nextInt(99999);
            System.out.println("OTP : " +randomNumber);
            user.setPassword(passwordEncoder.encode(randomNumber));
            userRepository.save(user);
        }
        return userRepository.save(user);
    }

    @Override
    public User getUserByMobileNumber(String mobileNumber) {
        return userRepository.findByMobileNumber(mobileNumber);
    }

    @Override
    public User sendOtp(String mobileNumber){
        User user = userRepository.findByMobileNumber(mobileNumber);
        if(user !=null){
            Random random = new Random();
            String randomNumber = "" + random.nextInt(99999);
            System.out.println("OTP : " +randomNumber);
            user.setPassword(passwordEncoder.encode(randomNumber));
            userRepository.save(user);
        }
        return user;
    }
}
