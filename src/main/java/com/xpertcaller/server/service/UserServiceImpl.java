package com.xpertcaller.server.service;

import com.xpertcaller.server.db.interfaces.dao.UserDao;
import com.xpertcaller.server.db.interfaces.dao.UserProfileDao;
import com.xpertcaller.server.db.sql.entities.UserEntity;
import com.xpertcaller.server.db.sql.entities.UserProfileEntity;
import com.xpertcaller.server.moduls.AddCategory;
import com.xpertcaller.server.moduls.User;
import com.xpertcaller.server.moduls.UserProfile;
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

    @Autowired
    UserProfileDao userProfileDao;
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
                userEntity.getPassword(), userEntity.getMobileNumber(), userEntity.isActive(), userEntity.getCategory(), userEntity.getRole(), userEntity.getOtp(), false, null);
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
    public User addCategory(AddCategory addCategory) {
        UserEntity userEntity = userDao.getUserById(addCategory.getUserId());
        UserProfileEntity userProfileEntity = userProfileDao.getUserProfileByUserId(addCategory.getUserId());
        if(userProfileEntity == null){
            userProfileEntity = new UserProfileEntity();
            userProfileEntity.setProfileId(UUID.randomUUID().toString());
            userProfileEntity.setUserId(addCategory.getUserId());
        }
        userProfileEntity.setExpertCategory(addCategory.getCategory());
        userProfileEntity.setSkills(addCategory.getSkills());
        userProfileDao.saveUserProfile(userProfileEntity);

        User user = convertUserEntityToUser(userEntity);
        user.setUserProfile(convertUserProfileEntityToUserProfile(userProfileEntity));
        return user;
    }
    private UserProfile convertUserProfileEntityToUserProfile(UserProfileEntity userProfileEntity){
        UserProfile userProfile = new UserProfile();
        userProfile.setProfileId(userProfileEntity.getProfileId());
        userProfile.setUserId(userProfileEntity.getUserId());
        userProfile.setExpertCategory(userProfile.getExpertCategory());
        userProfile.setSkills(userProfileEntity.getSkills());
        userProfile.setCollege(userProfileEntity.getCollege());
        userProfile.setDegree(userProfileEntity.getDegree());
        userProfile.setDetailedInfo(userProfileEntity.getDetailedInfo());
        userProfile.setProfilePicture(userProfileEntity.getProfilePicture());
        userProfile.setCertificates(userProfileEntity.getCertificates());
        userProfile.setDesignation(userProfileEntity.getDesignation());
        userProfile.setLanguages(userProfileEntity.getLanguages());
        userProfile.setLongitude(userProfileEntity.getLongitude());
        userProfile.setLatitude(userProfileEntity.getLatitude());
        userProfile.setAddress(userProfileEntity.getAddress());
        userProfile.setAverageRating(userProfileEntity.getAverageRating());
        return userProfile;
    }

    private User convertUserEntityToUser(UserEntity userEntity){
        return new User(userEntity.getUserId(), userEntity.getUsername(), userEntity.getEmail(), userEntity.getName(),
                userEntity.getAge(), "", userEntity.getMobileNumber(), userEntity.isActive(),
                userEntity.getCategory(), userEntity.getRole(), "", false, null);
    }

    private UserEntity convertUserToUserEntity(User user){
         return new UserEntity(user.getUserId(), user.getUsername(), user.getEmail(), user.getName(), user.getAge(),
                 user.getPassword(), user.getMobileNumber(), user.isActive(), user.getCategory(), user.getRole(), user.getOtp());
    }

}
