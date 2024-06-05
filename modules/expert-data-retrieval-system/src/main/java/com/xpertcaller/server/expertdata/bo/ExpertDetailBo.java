package com.xpertcaller.server.expertdata.bo;

import com.xpertcaller.server.common.exception.BusinessException;
import com.xpertcaller.server.expertdata.beans.ExpertAvailableTimeSlots;
import com.xpertcaller.server.expertdata.beans.ExpertDetails;
import com.xpertcaller.server.expertdata.beans.ScheduleMeeting;

import java.util.List;

public interface ExpertDetailBo {
    ExpertDetails fetchExpertDetails(String id);

    List<ExpertDetails> fetchAllExpertDetails();

    List<ExpertAvailableTimeSlots> fetchTimeSlotByUser(String userId, long date, String zone);

    List<ScheduleMeeting> getAllScheduleMeetingsBySubscriber() throws BusinessException;

    List<ScheduleMeeting> getAllScheduleMeetingsByPublisher() throws BusinessException;

    ScheduleMeeting addScheduleMeeting(ScheduleMeeting scheduleMeeting) throws BusinessException;

    ScheduleMeeting updateStatusOfMeeting(String meetingId, String status) throws BusinessException;
}
