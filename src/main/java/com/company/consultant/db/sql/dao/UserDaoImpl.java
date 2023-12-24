package com.company.consultant.db.sql.dao;

import com.company.consultant.db.interfaces.dao.UserDao;
import com.company.consultant.db.sql.entities.UserEntity;
import com.company.consultant.db.sql.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class UserDaoImpl implements UserDao {
    @Autowired
    UserRepository userRepository;

    @Override
    public Optional<UserEntity> findByUsername(String userName){
        return userRepository.findByUsername(userName);
    }
    @Override
    public UserEntity findByMobileNumber(String mobileNumber){
        return userRepository.findByMobileNumber(mobileNumber);
    }

    @Override
    public List<UserEntity> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public UserEntity saveUser(UserEntity userEntity){
        return userRepository.save(userEntity);
    }

    @Override
    public UserEntity getUserById(String id){
        return userRepository.getReferenceById(id);
    }
}
