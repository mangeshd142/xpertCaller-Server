package com.xpertcaller.server.expertdata.bo;

import com.xpertcaller.server.common.exception.BusinessException;
import com.xpertcaller.server.expertdata.beans.ExpertAvailableTimeSlots;
import com.xpertcaller.server.expertdata.beans.ExpertDetails;
import com.xpertcaller.server.expertdata.beans.ScheduleMeeting;
import com.xpertcaller.server.expertdata.beans.ScheduleMeetingFilter;
import com.xpertcaller.server.expertdata.beans.response.ScheduleMeetingResponse;

import java.util.List;

public interface ExpertDetailBo {
    ExpertDetails fetchExpertDetails(String id);

    List<ExpertDetails> fetchAllExpertDetails();

    List<ExpertAvailableTimeSlots> fetchTimeSlotByUser(String userId, long date, String zone);

    List<ScheduleMeetingResponse> getAllScheduleMeetingsBySubscriber() throws BusinessException;

    List<ScheduleMeetingResponse> getAllScheduleMeetingsBySubscriberOrPublisher(ScheduleMeetingFilter scheduleMeetingFilter) throws BusinessException;

    List<ScheduleMeetingResponse> getAllScheduleMeetingsByPublisher() throws BusinessException;

    ScheduleMeetingResponse addScheduleMeeting(ScheduleMeeting scheduleMeeting) throws BusinessException;

    ScheduleMeetingResponse updateStatusOfMeeting(String meetingId, int status) throws BusinessException;
}
