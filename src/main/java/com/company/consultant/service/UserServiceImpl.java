package com.company.consultant.service;

import com.company.consultant.db.interfaces.dao.UserDao;
import com.company.consultant.db.sql.entities.UserEntity;
import com.company.consultant.moduls.User;
import com.company.consultant.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userDao;

    @Autowired
    private PasswordEncoder passwordEncoder;
    Random random = new Random();

    @Override
    public List<User> getAllUsers(){
        List<UserEntity> userEntityList = userDao.getAllUsers();
        List<User> userList = new ArrayList<>();
        for (UserEntity userEntity : userEntityList){
            userList.add(new User(userEntity.getUserId(), userEntity.getUsername(), userEntity.getEmail(), userEntity.getName(), userEntity.getAge(),
                    "", userEntity.getMobileNumber(), userEntity.isActive(), userEntity.getCategory(), userEntity.getRole(), userEntity.getOtp(), false));
        }
        return userList;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = userDao.findByMobileNumber(username);
        return new User(userEntity.getUserId(), userEntity.getUsername(), userEntity.getEmail(), userEntity.getName(), userEntity.getAge(),
                userEntity.getPassword(), userEntity.getMobileNumber(), userEntity.isActive(), userEntity.getCategory(), userEntity.getRole(), userEntity.getOtp(), false);
    }

    @Override
    public User createUser(User user){
        user.setUserId(UUID.randomUUID().toString());
        user.setActive(true);
        if(user != null){
            String randomNumber = "" + random.nextInt(10000,99999);
            System.err.println("OTP : " +randomNumber);
            user.setPassword(passwordEncoder.encode(randomNumber));

            UserEntity userEntity = new UserEntity(user.getUserId(), user.getUsername(), user.getEmail(), user.getName(), user.getAge(),
                    user.getPassword(), user.getMobileNumber(), user.isActive(), user.getCategory(), user.getRole(), "");
            UserEntity userEntity1 = userDao.saveUser(userEntity);
            user = new User(userEntity1.getUserId(), userEntity1.getUsername(), userEntity1.getEmail(), userEntity1.getName(), userEntity1.getAge(),
                    "", userEntity1.getMobileNumber(), userEntity1.isActive(), userEntity1.getCategory(), userEntity1.getRole(), "", false);
        }
        return user;
    }

    @Override
    public User getUserByMobileNumber(String mobileNumber) {
        UserEntity userEntity = userDao.findByMobileNumber(mobileNumber);
        return new User(userEntity.getUserId(), userEntity.getUsername(), userEntity.getEmail(), userEntity.getName(), userEntity.getAge(),
                "", userEntity.getMobileNumber(), userEntity.isActive(), userEntity.getCategory(), userEntity.getRole(), "", false);

    }

    @Override
    public User sendOtp(String mobileNumber){
        UserEntity userEntity = userDao.findByMobileNumber(mobileNumber);
        if(userEntity !=null){
            String randomNumber = "" + random.nextInt(99999);
            System.out.println("OTP : " +randomNumber);
            userEntity.setOtp(passwordEncoder.encode(randomNumber));
            userEntity = userDao.saveUser(userEntity);
        }
        return new User(userEntity.getUserId(), userEntity.getUsername(), userEntity.getEmail(), userEntity.getName(),
                userEntity.getAge(), "", userEntity.getMobileNumber(), userEntity.isActive(),
                userEntity.getCategory(), userEntity.getRole(), "", false);
    }

    @Override
    public void deleteOtp(String mobileNumber){
        UserEntity userEntity = userDao.findByMobileNumber(mobileNumber);
        userEntity.setOtp("");
        userDao.saveUser(userEntity);
    }
}
