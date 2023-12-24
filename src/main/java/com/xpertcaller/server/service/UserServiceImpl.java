package com.xpertcaller.server.service;

import com.xpertcaller.server.db.interfaces.dao.UserDao;
import com.xpertcaller.server.db.sql.entities.UserEntity;
import com.xpertcaller.server.moduls.AddCategory;
import com.xpertcaller.server.moduls.User;
import com.xpertcaller.server.service.interfaces.UserService;
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
            userList.add(convertUserEntityToUser(userEntity));
        }
        return userList;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = userDao.findByMobileNumber(username);
        return new User(userEntity.getUserId(), userEntity.getUsername(), userEntity.getEmail(), userEntity.getName(), userEntity.getAge(),
                userEntity.getPassword(), userEntity.getMobileNumber(), userEntity.isActive(), userEntity.getCategory(), userEntity.getProfileId(), userEntity.getRole(), userEntity.getOtp(), false);
    }

    @Override
    public User createUser(User user){
        user.setUserId(UUID.randomUUID().toString());
        user.setActive(true);
        if(user != null){
            String randomNumber = "" + random.nextInt(10000,99999);
            System.err.println("OTP : " +randomNumber);
            String password = passwordEncoder.encode(randomNumber);
            user.setPassword(password);
            user.setOtp(password);
            UserEntity userEntity = convertUserToUserEntity(user);
            UserEntity userEntity1 = userDao.saveUser(userEntity);
            user = convertUserEntityToUser(userEntity);
        }
        return user;
    }

    @Override
    public User getUserByMobileNumber(String mobileNumber) {
        UserEntity userEntity = userDao.findByMobileNumber(mobileNumber);
        return convertUserEntityToUser(userEntity);
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
        return convertUserEntityToUser(userEntity);
    }

    @Override
    public void deleteOtp(String mobileNumber){
        UserEntity userEntity = userDao.findByMobileNumber(mobileNumber);
        userEntity.setOtp("");
        userDao.saveUser(userEntity);
    }

    @Override
    public AddCategory addCategory(AddCategory addCategory) {
        UserEntity userEntity = userDao.getUserById(addCategory.getUserId());
        userEntity.setCategory(addCategory.getCategory());

        return null;
    }

    private User convertUserEntityToUser(UserEntity userEntity){
        return new User(userEntity.getUserId(), userEntity.getUsername(), userEntity.getEmail(), userEntity.getName(),
                userEntity.getAge(), "", userEntity.getMobileNumber(), userEntity.isActive(),
                userEntity.getCategory(), userEntity.getProfileId(), userEntity.getRole(), "", false);
    }

    private UserEntity convertUserToUserEntity(User user){
         return new UserEntity(user.getUserId(), user.getUsername(), user.getEmail(), user.getName(), user.getAge(),
                 user.getPassword(), user.getMobileNumber(), user.isActive(), user.getProfileId(), user.getCategory(), user.getRole(), user.getOtp());
    }

}
