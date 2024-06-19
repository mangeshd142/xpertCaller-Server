package com.xpertcaller.server.expertdata.serviceimpl;

import com.xpertcaller.server.common.exception.BusinessException;
import com.xpertcaller.server.expertdata.beans.ExpertAvailableTimeSlots;
import com.xpertcaller.server.expertdata.beans.ExpertDetails;
import com.xpertcaller.server.expertdata.beans.ScheduleMeeting;
import com.xpertcaller.server.expertdata.beans.response.ScheduleMeetingResponse;
import com.xpertcaller.server.expertdata.bo.ExpertDetailBo;
import com.xpertcaller.server.expertdata.service.ExpertDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExpertDetailServiceImpl implements ExpertDetailService {

    @Autowired
    ExpertDetailBo expertDetailBo;

    @Override
    public ExpertDetails fetchExpertDetails(String id) {
        return expertDetailBo.fetchExpertDetails(id);
    }

    @Override
    public List<ExpertDetails> fetchAllExpertDetails(){
        return expertDetailBo.fetchAllExpertDetails();
    }

    @Override
    public List<ExpertAvailableTimeSlots> fetchTimeSlotByUser(String userId, long startDateL, String zone){
        return expertDetailBo.fetchTimeSlotByUser(userId, startDateL, zone);
    }

    @Override
    public ScheduleMeetingResponse addScheduleMeeting(ScheduleMeeting scheduleMeeting) throws BusinessException {
        return expertDetailBo.addScheduleMeeting(scheduleMeeting);
    }

    @Override
    public ScheduleMeetingResponse updateStatusOfMeeting(String meetingId, int status) throws BusinessException {
        return expertDetailBo.updateStatusOfMeeting(meetingId, status);
    }

    @Override
    public List<ScheduleMeetingResponse> getAllScheduleMeetingsBySubscriber() throws BusinessException {
        return expertDetailBo.getAllScheduleMeetingsBySubscriber();
    }

    @Override
    public List<ScheduleMeetingResponse> getAllScheduleMeetingsByPublisher() throws BusinessException {
        return expertDetailBo.getAllScheduleMeetingsByPublisher();
    }

    @Override
    public List<ScheduleMeetingResponse> getAllScheduleMeetingsBySubscriberOrPublisher() throws BusinessException {
        return expertDetailBo.getAllScheduleMeetingsBySubscriberOrPublisher();
    }
}
