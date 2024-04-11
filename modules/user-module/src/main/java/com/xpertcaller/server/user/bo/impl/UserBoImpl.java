package com.xpertcaller.server.user.bo.impl;

import com.xpertcaller.server.common.exception.BusinessException;
import com.xpertcaller.server.user.beans.user.Address;
import com.xpertcaller.server.user.beans.user.AvailableTimeSlot;
import com.xpertcaller.server.user.beans.user.AvailableTimeSlotRequest;
import com.xpertcaller.server.user.beans.user.User;
import com.xpertcaller.server.user.bo.interfaces.UserBo;
import com.xpertcaller.server.user.db.interfaces.dao.AvailableTimeSlotDao;
import com.xpertcaller.server.user.db.interfaces.dao.UserDao;
import com.xpertcaller.server.user.db.sql.entities.UserEntity;
import com.xpertcaller.server.user.db.sql.entities.profileEntities.AddressEntity;
import com.xpertcaller.server.user.db.sql.entities.profileEntities.AvailableTimeSlotEntity;
import com.xpertcaller.server.user.db.sql.repositories.AddressRepository;
import com.xpertcaller.server.user.util.CommonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class UserBoImpl implements UserBo {

    @Autowired
    AddressRepository addressRepository;

    @Autowired
    AvailableTimeSlotDao availableTimeSlotDao;
    @Autowired
    UserDao userDao;

    private static final Logger logger = LoggerFactory.getLogger(UserBoImpl.class);

    @Override
    public User updateUser(User user) throws BusinessException{
        User loggedUser = CommonUtil.getCurrentUser();
        UserEntity userEntity2 = userDao.findByMobileNumber(loggedUser.getMobileNumber());
        if(userEntity2 != null) {
            userEntity2.setName(getValue(user.getName(), userEntity2.getName()));
            userEntity2.setAbout(getValue(user.getAbout(), userEntity2.getAbout()));
            userEntity2.setEmail(getValue(user.getEmail(), userEntity2.getEmail()));
            userEntity2.setUsername( getValue(user.getUsername(), userEntity2.getUsername()));
            userEntity2.setMobileNumber(getValue(user.getMobileNumber(), userEntity2.getMobileNumber()));
            userEntity2.setGender(getValue(user.getGender(), userEntity2.getGender()));
            Address address = user.getAddress();
            AddressEntity addressEntity = null;
            if(address != null){
                addressEntity = new AddressEntity();
                if(address.getId() != null)
                    addressEntity.setAddressId(address.getId());

                addressEntity.setStreet(address.getStreet());
                addressEntity.setCity(address.getCity());
                addressEntity.setLatitude(address.getLatitude());
                addressEntity.setState(address.getState());
                addressEntity.setZipCode(address.getZipCode());
                addressEntity.setLongitude(address.getLongitude());
                addressEntity = addressRepository.save(addressEntity);
                userEntity2.setAddressEntity(addressEntity);
            }
        }else {
            throw new BusinessException("User not available, please register user");
        }
        return convertUserEntityToUser(userDao.saveUser(userEntity2));
    }

    @Override
    public List<AvailableTimeSlot> addAvailableTimeSlots(List<AvailableTimeSlot> availableTimeSlotList) throws BusinessException {

        try {
            List<AvailableTimeSlotEntity> availableTimeSlotEntityList = new ArrayList<>();
            List<AvailableTimeSlotEntity> finalAvailableTimeSlotEntityList = new ArrayList<>();
            User user = CommonUtil.getCurrentUser();
            UserEntity userEntity = userDao.findByMobileNumber(user.getMobileNumber());
            availableTimeSlotList.forEach(availableTimeSlot -> {
                AvailableTimeSlotEntity availableTimeSlotEntity = new AvailableTimeSlotEntity();
                availableTimeSlotEntity.setZone(availableTimeSlot.getZone());
                Date startTime = new Date(availableTimeSlot.getStartTime());
                availableTimeSlotEntity.setStartTime(startTime);
                Date endTime = new Date(availableTimeSlot.getEndTime());
                availableTimeSlotEntity.setEndTime(endTime);
                availableTimeSlotEntity.setUserEntity(userEntity);
                availableTimeSlotEntityList.add(availableTimeSlotEntity);
            });
            if (!availableTimeSlotEntityList.isEmpty())
                finalAvailableTimeSlotEntityList = availableTimeSlotDao.addAllSchedule(availableTimeSlotEntityList);

            return convertAvailableTimeSlotEntityListToAvailableTimeSlotList(finalAvailableTimeSlotEntityList);
        }catch (Exception e){
            logger.error("Error while inserting available time slots: ", e);
            throw new BusinessException("Error while adding time slots");
        }

    }

    @Override
    public List<AvailableTimeSlot> getAllTimeSlots() throws BusinessException {
        User user = CommonUtil.getCurrentUser();
        List<AvailableTimeSlotEntity> availableTimeSlotEntityList = availableTimeSlotDao.getAllTimeSlotsOfCurrentUser(user.getUserId());
        return convertAvailableTimeSlotEntityListToAvailableTimeSlotList(availableTimeSlotEntityList);
    }

    @Override
    public List<AvailableTimeSlot> getAvailableTimeSlotsByDate(AvailableTimeSlotRequest availableTimeSlotRequest) throws BusinessException {
        User user = CommonUtil.getCurrentUser();
        String zone = availableTimeSlotRequest.getZone();
        long startDateL = availableTimeSlotRequest.getDate();
        long twentyThreeHoursInMillis = 23 * 60 * 60 * 1000L;
        long fiftyNineMinutesInMillis = 59 * 60 * 1000L;
        long endDateL = startDateL + twentyThreeHoursInMillis + fiftyNineMinutesInMillis;

        Date startDate = new Date(startDateL);
        Date endDate = new Date(endDateL);

        return convertAvailableTimeSlotEntityListToAvailableTimeSlotList(
                availableTimeSlotDao.getTimeSlotsOfStartDateInBetween(user.getUserId(), startDate,endDate));

    }


    private List<AvailableTimeSlot> convertAvailableTimeSlotEntityListToAvailableTimeSlotList(List<AvailableTimeSlotEntity> availableTimeSlotEntityList){
        List<AvailableTimeSlot> availableTimeSlotList = new ArrayList<>();
        if(availableTimeSlotEntityList != null) {
            availableTimeSlotEntityList.forEach(availableTimeSlotEntity -> {
                availableTimeSlotList.add(convertAvailableTimeSlotEntityToAvailableTimeSlot(availableTimeSlotEntity));
            });
        }
        return availableTimeSlotList;
    }
    private AvailableTimeSlot convertAvailableTimeSlotEntityToAvailableTimeSlot(AvailableTimeSlotEntity availableTimeSlotEntity){
        AvailableTimeSlot availableTimeSlot = new AvailableTimeSlot();
        availableTimeSlot.setZone(availableTimeSlotEntity.getZone());
        availableTimeSlot.setStartTime(availableTimeSlotEntity.getStartTime().getTime());
        availableTimeSlot.setEndTime(availableTimeSlotEntity.getEndTime().getTime());
        availableTimeSlot.setId(availableTimeSlotEntity.getTimeSlotId());

        return availableTimeSlot;
    }

    private String getValue(String newValue, String value){
        return newValue != null && !newValue.isEmpty() ? newValue : value;
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
                userEntity.getAge(), userEntity.getAbout(), address, userEntity.getGender(),"", userEntity.getMobileNumber(), userEntity.isActive(),
                userEntity.getRole(), "", false);
    }
}
