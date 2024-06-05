package com.xpertcaller.server.user.service.interfaces;

import com.xpertcaller.server.common.exception.BusinessException;
import com.xpertcaller.server.user.beans.user.*;
import com.xpertcaller.server.user.db.sql.entities.profileEntities.AvailableTimeSlotChunksEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;
import java.util.Map;

public interface UserService {
    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
    User createUser(User user);

    User getUserByMobileNumber(String mobileNumber);
    User sendOtp(String mobileNumber) throws BusinessException;
    List<User> getAllUsers();

    void deleteOtp(String mobileNumber);

    ProfileDetails addProfileDetails(ProfileDetails profileDetails) throws BusinessException;

    User updateUser(User user) throws BusinessException;

    ProfileDetails updateProfilePictureId(String profileImageId) throws BusinessException;

    ProfileDetails updateDocumentIds(List<String> profilePicIds) throws BusinessException;

    ProfileDetails fetchProfileDetails() throws BusinessException;

    User fetchCurrentUser() throws BusinessException;

    List<AvailableTimeSlot> addAvailableTimeSlots(List<AvailableTimeSlot> availableTimeSlotList) throws BusinessException;

    List<AvailableTimeSlot> getAllTimeSlots() throws BusinessException;

    List<AvailableTimeSlot> getAvailableTimeSlotsByDate(AvailableTimeSlotRequest availableTimeSlotRequest) throws BusinessException;

    List<AvailableTimeSlotChunks> getAvailableTimeslotChunksByTimeSlotId(String timeSlotId);

    String deleteDocument(String fileName) throws BusinessException;

    List<User> createUsers(Map<String, List<User>> userMap);

    void updateAvailableTimeslotChunkStatus(String id, String status) throws BusinessException;
}
