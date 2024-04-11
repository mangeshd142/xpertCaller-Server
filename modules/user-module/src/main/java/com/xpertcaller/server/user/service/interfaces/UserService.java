package com.xpertcaller.server.user.service.interfaces;

import com.xpertcaller.server.common.exception.BusinessException;
import com.xpertcaller.server.user.beans.user.AvailableTimeSlot;
import com.xpertcaller.server.user.beans.user.AvailableTimeSlotRequest;
import com.xpertcaller.server.user.beans.user.ProfileDetails;
import com.xpertcaller.server.user.beans.user.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

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
}
