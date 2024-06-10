package com.xpertcaller.server.expertdata.service;

import com.xpertcaller.server.common.exception.BusinessException;
import com.xpertcaller.server.expertdata.beans.ExpertAvailableTimeSlots;
import com.xpertcaller.server.expertdata.beans.ExpertDetails;
import com.xpertcaller.server.expertdata.beans.ScheduleMeeting;

import java.util.List;

public interface ExpertDetailService {
    ExpertDetails fetchExpertDetails(String id);

    List<ExpertDetails> fetchAllExpertDetails();

    List<ExpertAvailableTimeSlots> fetchTimeSlotByUser(String userId, long startDateL, String zone);

    ScheduleMeeting addScheduleMeeting(ScheduleMeeting scheduleMeeting) throws BusinessException;

    ScheduleMeeting updateStatusOfMeeting(String meetingId, int status) throws BusinessException;

    List<ScheduleMeeting> getAllScheduleMeetingsBySubscriber() throws BusinessException;

    List<ScheduleMeeting> getAllScheduleMeetingsByPublisher() throws BusinessException;
}
