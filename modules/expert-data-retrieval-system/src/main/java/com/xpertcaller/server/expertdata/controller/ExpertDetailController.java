package com.xpertcaller.server.expertdata.controller;

import com.xpertcaller.server.common.exception.BusinessException;
import com.xpertcaller.server.expertdata.beans.*;
import com.xpertcaller.server.expertdata.beans.request.ExpertFilter;
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

    /**
     * Fetch expert details by id
     * @param id
     * @return ExpertDetails
     * @throws BusinessException
     */
    @CrossOrigin(origins = "*")
    @GetMapping(value = "/fetchExpert/{id}")
    public ExpertDetails fetchExpert(@PathVariable String id){
        return expertDetailService.fetchExpertDetails(id);
    }

    /**
     * Fetch all expert details
     * @param
     * @return List of ExpertDetails
     * @throws BusinessException
     */
    @CrossOrigin(origins = "*")
    @GetMapping(value = "/fetchAllExpert")
    public Map<String , List<ExpertDetails> > fetchAllExpert(){
        Map<String , List<ExpertDetails>> expertMap = new HashMap<>();
        expertMap.put("experts", expertDetailService.fetchAllExpertDetails());
        return expertMap;
    }

    /**
     * Fetch expert details by filter
     * @param expertFilter
     * @return List of ExpertDetails
     * @throws BusinessException
     */
    @CrossOrigin(origins = "*")
    @PostMapping(value = "/fetchExperts")
    public Map<String , List<ExpertDetails> > fetchExperts(@RequestBody ExpertFilter expertFilter) throws BusinessException {
        Map<String , List<ExpertDetails>> expertMap = new HashMap<>();
        expertMap.put("experts", expertDetailService.fetchExpertDetailsByFilter(expertFilter));
        return expertMap;
    }

    /**
     * Fetch expert available time slots
     * @param availableTimeSlotRequest
     * @return Map of String and List of ExpertAvailableTimeSlots
     */
    @CrossOrigin(origins = "*")
    @PostMapping(value = "/fetchExpertTimeSlots")
    public Map<String , List<ExpertAvailableTimeSlots> > fetchExpertTimeSlots(@RequestBody AvailableTimeSlotRequest availableTimeSlotRequest){
        Map<String , List<ExpertAvailableTimeSlots>> expertAvailableSlotMap = new HashMap<>();
        expertAvailableSlotMap.put("availableTimeSlots", expertDetailService.fetchTimeSlotByUser(availableTimeSlotRequest.getUserId(),
                availableTimeSlotRequest.getDate(), availableTimeSlotRequest.getZone()));
        return expertAvailableSlotMap;
    }

    /**
     * fetch Publisher Schedule Meetings
     * @return
     * @throws BusinessException
     */
    @CrossOrigin(origins = "*")
    @GetMapping(value = "/fetchPublisherScheduleMeetings")
    public List<ScheduleMeetingResponse> fetchPublisherScheduleMeetings() throws BusinessException {
        return expertDetailService.getAllScheduleMeetingsByPublisher();
    }

    /**
     * fetch Subscriber Schedule Meetings
     * @return List of ScheduleMeetingResponse
     * @throws BusinessException
     */
    @CrossOrigin(origins = "*")
    @GetMapping(value = "/fetchSubscriberScheduleMeetings")
    public List<ScheduleMeetingResponse> fetchSubscriberScheduleMeetings() throws BusinessException {
        return expertDetailService.getAllScheduleMeetingsBySubscriber();
    }

    /**
     * fetch all Schedule Meetings
     * @param scheduleMeetingFilter
     * @return List of ScheduleMeetingResponse
     * @throws BusinessException
     */
    @CrossOrigin(origins = "*")
    @PostMapping(value = "/meetings")
    public List<ScheduleMeetingResponse> fetchMeetings(@RequestBody ScheduleMeetingFilter scheduleMeetingFilter) throws BusinessException {
        return expertDetailService.getAllScheduleMeetingsBySubscriberOrPublisher(scheduleMeetingFilter);
    }

    /**
     * schedule meeting
     * @param scheduleMeeting
     * @return ScheduleMeetingResponse
     * @throws BusinessException
     */
    @CrossOrigin(origins = "*")
    @PostMapping(value = "/scheduleMeeting")
    public ScheduleMeetingResponse scheduleMeeting(@RequestBody ScheduleMeeting scheduleMeeting) throws BusinessException {
        return expertDetailService.addScheduleMeeting(scheduleMeeting);
    }

    /**
     * update meeting status
     * @param meetingId
     * @param status
     * @return ScheduleMeetingResponse
     * @throws BusinessException
     */
    @CrossOrigin(origins = "*")
    @GetMapping(value = "/updateMeetingStatus/{meetingId}/{status}")
    public ScheduleMeetingResponse updateStatusOfMeeting(@PathVariable String meetingId, @PathVariable int status) throws BusinessException {
        return expertDetailService.updateStatusOfMeeting(meetingId, status);
    }
}
