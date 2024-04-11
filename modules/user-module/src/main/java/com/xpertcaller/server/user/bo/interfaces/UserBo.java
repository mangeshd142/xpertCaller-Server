package com.xpertcaller.server.user.bo.interfaces;

import com.xpertcaller.server.common.exception.BusinessException;
import com.xpertcaller.server.user.beans.user.AvailableTimeSlot;
import com.xpertcaller.server.user.beans.user.AvailableTimeSlotRequest;
import com.xpertcaller.server.user.beans.user.User;

import java.util.List;

public interface UserBo {
    User updateUser(User user) throws BusinessException;

    List<AvailableTimeSlot> addAvailableTimeSlots(List<AvailableTimeSlot> availableTimeSlotList) throws BusinessException;

    List<AvailableTimeSlot> getAllTimeSlots() throws BusinessException;

    List<AvailableTimeSlot> getAvailableTimeSlotsByDate(AvailableTimeSlotRequest availableTimeSlotRequest) throws BusinessException;
}
