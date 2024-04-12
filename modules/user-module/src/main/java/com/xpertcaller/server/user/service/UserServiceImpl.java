package com.xpertcaller.server.user.service;

import com.xpertcaller.server.common.exception.BusinessException;
import com.xpertcaller.server.user.beans.user.*;
import com.xpertcaller.server.user.bo.interfaces.UserBo;
import com.xpertcaller.server.user.bo.interfaces.UserProfileBo;
import com.xpertcaller.server.user.db.interfaces.dao.UserDao;
import com.xpertcaller.server.user.db.sql.entities.UserEntity;
import com.xpertcaller.server.user.db.sql.entities.profileEntities.AddressEntity;
import com.xpertcaller.server.user.db.sql.repositories.AddressRepository;
import com.xpertcaller.server.user.service.interfaces.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    UserProfileBo userProfileBo;
    @Autowired
    AddressRepository addressRepository;

    @Autowired
    UserBo userBo;

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
        AddressEntity addressEntity = userEntity.getAddressEntity();
        Address address = new Address();
        if(addressEntity != null){
            address.setId(addressEntity.getAddressId());
            address.setStreet(addressEntity.getStreet());
            address.setCity(addressEntity.getCity());
            address.setState(addressEntity.getState());
            address.setZipCode(addressEntity.getZipCode());
            address.setLatitude(addressEntity.getLatitude());
            address.setLongitude(addressEntity.getLongitude());
        }
        return new User(userEntity.getUserId(), userEntity.getUsername(), userEntity.getEmail(), userEntity.getName(),
                userEntity.getAge(), userEntity.getAbout(), address, userEntity.getGender(), userEntity.getProfilePic(), userEntity.getPassword(), userEntity.getMobileNumber(), userEntity.isActive(),
                userEntity.getRole(), userEntity.getOtp(), false);
    }

    @Override
    public User createUser(User user){
        UserEntity userEntity = userDao.findByMobileNumber(user.getMobileNumber());
        if(userEntity == null){
            user.setUserId(UUID.randomUUID().toString());
            user.setActive(true);
            String randomNumber = "" + random.nextInt(10000,99999);
            logger.error("OTP : {}", randomNumber);
            String password = passwordEncoder.encode(randomNumber);
            user.setPassword(password);
            user.setOtp(password);
            userEntity = convertUserToUserEntity(user);
            AddressEntity addressEntity = userEntity.getAddressEntity();
            if(addressEntity != null)
                addressRepository.save(addressEntity);

            UserEntity userEntity1 = userDao.saveUser(userEntity);
            user = convertUserEntityToUser(userEntity1);
        }
        return user;
    }

    @Override
    public User getUserByMobileNumber(String mobileNumber) {
        UserEntity userEntity = userDao.findByMobileNumber(mobileNumber);
        return convertUserEntityToUser(userEntity);
    }

    @Override
    public User sendOtp(String mobileNumber) throws BusinessException {
        try {
            UserEntity userEntity = userDao.findByMobileNumber(mobileNumber);
            if (userEntity == null) {
                userEntity = new UserEntity();
                userEntity.setUserId(UUID.randomUUID().toString());
                userEntity.setMobileNumber(mobileNumber);
                userEntity.setUsername(mobileNumber);
                userEntity.setActive(true);
            }
            String randomNumber = "" + random.nextInt(10000, 99999);
            logger.error("OTP : {}", randomNumber);
            userEntity.setOtp(passwordEncoder.encode(randomNumber));
            return convertUserEntityToUser(userDao.saveUser(userEntity));
        } catch (Exception e) {
            logger.error("Error while sending otp or creating new user ", e);
            throw new BusinessException("Error while sending otp or creating new user");
        }
    }

    @Override
    public void deleteOtp(String mobileNumber){
        UserEntity userEntity = userDao.findByMobileNumber(mobileNumber);
        userEntity.setOtp("");
        userDao.saveUser(userEntity);
    }



    @Override
    public ProfileDetails addProfileDetails(ProfileDetails profileDetails) throws BusinessException {
        return userProfileBo.addOrUpdateProfileDetails(profileDetails);
    }

    @Override
    public User updateUser(User user) throws BusinessException {
        return userBo.updateUser(user);
    }

    @Override
    public ProfileDetails updateProfilePictureId(String profileImageId) throws BusinessException {
        return userProfileBo.updateProfilePictureId(profileImageId);
    }

    public ProfileDetails updateDocumentIds(List<String> profilePicIds) throws BusinessException {
        return userProfileBo.updateDocumentIds(profilePicIds);
    }

    @Override
    public User fetchCurrentUser() throws BusinessException{
        return userProfileBo.fetchCurrentUser();
    }
    @Override
    public ProfileDetails fetchProfileDetails() throws BusinessException{
        return userProfileBo.fetchProfileDetails();
    }

    @Override
    public List<AvailableTimeSlot> addAvailableTimeSlots(List<AvailableTimeSlot> availableTimeSlotList) throws BusinessException{
        return userBo.addAvailableTimeSlots(availableTimeSlotList);
    }

    @Override
    public List<AvailableTimeSlot> getAllTimeSlots() throws BusinessException{
        return userBo.getAllTimeSlots();
    }

    @Override
    public List<AvailableTimeSlot> getAvailableTimeSlotsByDate(AvailableTimeSlotRequest availableTimeSlotRequest) throws BusinessException{
       return userBo.getAvailableTimeSlotsByDate(availableTimeSlotRequest);
    }

    private User convertUserEntityToUser(UserEntity userEntity){
        AddressEntity addressEntity = userEntity.getAddressEntity();
        Address address = new Address();
        if(addressEntity != null){
            address.setId(addressEntity.getAddressId());
            address.setStreet(addressEntity.getStreet());
            address.setCity(addressEntity.getCity());
            address.setState(addressEntity.getState());
            address.setZipCode(addressEntity.getZipCode());
            address.setLatitude(addressEntity.getLatitude());
            address.setLongitude(addressEntity.getLongitude());
        }
        return new User(userEntity.getUserId(), userEntity.getUsername(), userEntity.getEmail(), userEntity.getName(),
                userEntity.getAge(), userEntity.getAbout(), address, userEntity.getGender(), userEntity.getProfilePic(), "", userEntity.getMobileNumber(), userEntity.isActive(),
                userEntity.getRole(), "", false);
    }

    private UserEntity convertUserToUserEntity(User user){
        Address address = user.getAddress();
        AddressEntity addressEntity = null;
        if(address != null){
            addressEntity = new AddressEntity();
            addressEntity.setStreet(address.getStreet());
            addressEntity.setCity(address.getCity());
            addressEntity.setLatitude(address.getLatitude());
            addressEntity.setLongitude(address.getLongitude());
        }
        return new UserEntity(user.getUserId(), user.getUsername(), user.getEmail(), user.getName(), user.getAge(), user.getAbout(), addressEntity, user.getGender(),
                 user.getProfilePic(), user.getPassword(), user.getMobileNumber(), user.isActive(), user.getRole(), user.getOtp());
    }

}
