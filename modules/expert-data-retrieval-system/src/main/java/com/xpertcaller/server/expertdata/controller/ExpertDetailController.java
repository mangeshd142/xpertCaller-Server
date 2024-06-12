package com.xpertcaller.server.expertdata.controller;

import com.xpertcaller.server.common.exception.BusinessException;
import com.xpertcaller.server.expertdata.beans.AvailableTimeSlotRequest;
import com.xpertcaller.server.expertdata.beans.ExpertAvailableTimeSlots;
import com.xpertcaller.server.expertdata.beans.ExpertDetails;
import com.xpertcaller.server.expertdata.beans.ScheduleMeeting;
import com.xpertcaller.server.expertdata.beans.response.ScheduleMeetingResponse;
import com.xpertcaller.server.expertdata.service.ExpertDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/expert")
public class ExpertDetailController {

    @Autowired
    ExpertDetailService expertDetailService;

    @CrossOrigin(origins = "*")
    @GetMapping(value = "/fetchExpert/{id}")
    public ExpertDetails fetchExpert(@PathVariable String id){
        return expertDetailService.fetchExpertDetails(id);
    }

    @CrossOrigin(origins = "*")
    @GetMapping(value = "/fetchAllExpert")
    public Map<String , List<ExpertDetails> > fetchAllExpert(){
        Map<String , List<ExpertDetails>> expertMap = new HashMap<>();
        expertMap.put("experts", expertDetailService.fetchAllExpertDetails());
        return expertMap;
    }

    @CrossOrigin(origins = "*")
    @PostMapping(value = "/fetchExpertTimeSlots")
    public Map<String , List<ExpertAvailableTimeSlots> > fetchExpertTimeSlots(@RequestBody AvailableTimeSlotRequest availableTimeSlotRequest){
        Map<String , List<ExpertAvailableTimeSlots>> expertAvailableSlotMap = new HashMap<>();
        expertAvailableSlotMap.put("availableTimeSlots", expertDetailService.fetchTimeSlotByUser(availableTimeSlotRequest.getUserId(),
                availableTimeSlotRequest.getDate(), availableTimeSlotRequest.getZone()));
        return expertAvailableSlotMap;
    }

    @CrossOrigin(origins = "*")
    @GetMapping(value = "/fetchPublisherScheduleMeetings")
    public List<ScheduleMeetingResponse> fetchPublisherScheduleMeetings() throws BusinessException {
        return expertDetailService.getAllScheduleMeetingsByPublisher();
    }

    @CrossOrigin(origins = "*")
    @GetMapping(value = "/fetchSubscriberScheduleMeetings")
    public List<ScheduleMeetingResponse> fetchSubscriberScheduleMeetings() throws BusinessException {
        return expertDetailService.getAllScheduleMeetingsBySubscriber();
    }

    @CrossOrigin(origins = "*")
    @PostMapping(value = "/scheduleMeeting")
    public ScheduleMeetingResponse scheduleMeeting(@RequestBody ScheduleMeeting scheduleMeeting) throws BusinessException {
        return expertDetailService.addScheduleMeeting(scheduleMeeting);
    }

    @CrossOrigin(origins = "*")
    @GetMapping(value = "/updateMeetingStatus/{meetingId}/{status}")
    public ScheduleMeetingResponse updateStatusOfMeeting(@PathVariable String meetingId, @PathVariable int status) throws BusinessException {
        return expertDetailService.updateStatusOfMeeting(meetingId, status);
    }
}
