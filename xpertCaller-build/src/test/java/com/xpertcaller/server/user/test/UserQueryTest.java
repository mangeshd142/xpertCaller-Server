package com.xpertcaller.server.user.test;

import com.xpertcaller.server.XpertCallerApplication;
import com.xpertcaller.server.user.db.sql.entities.UserEntity;
import com.xpertcaller.server.user.db.sql.entities.profileEntities.UserProfileEntity;
import com.xpertcaller.server.user.db.sql.repositories.UserProfileRepository;
import com.xpertcaller.server.user.db.sql.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest(classes=XpertCallerApplication.class)
public class UserQueryTest {
    @Autowired
    UserRepository userRepository;


    //@Test
    //@Transactional
    void testUserRepo(){
        try {
            Pageable pageable = PageRequest.of(1, 2);
            List<UserEntity> userEntityList = userRepository.findByCategory("", "", pageable);
            System.out.println("size : "+userEntityList.size());
            userEntityList.forEach(userEntity -> {
                System.out.println("User Id : "+userEntity.getUserId());
                UserProfileEntity userProfileEntity1 = userEntity.getUserProfileEntity();
                System.out.println("ProfileId : "+userProfileEntity1.getProfileId());
            });
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
