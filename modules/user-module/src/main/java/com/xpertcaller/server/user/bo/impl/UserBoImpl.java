package com.xpertcaller.server.user.bo.impl;

import com.xpertcaller.server.common.exception.BusinessException;
import com.xpertcaller.server.user.beans.user.Address;
import com.xpertcaller.server.user.beans.user.User;
import com.xpertcaller.server.user.bo.interfaces.UserBo;
import com.xpertcaller.server.user.db.interfaces.dao.UserDao;
import com.xpertcaller.server.user.db.sql.entities.UserEntity;
import com.xpertcaller.server.user.db.sql.entities.profileEntities.AddressEntity;
import com.xpertcaller.server.user.db.sql.repositories.AddressRepository;
import com.xpertcaller.server.user.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserBoImpl implements UserBo {

    @Autowired
    AddressRepository addressRepository;
    @Autowired
    UserDao userDao;
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
