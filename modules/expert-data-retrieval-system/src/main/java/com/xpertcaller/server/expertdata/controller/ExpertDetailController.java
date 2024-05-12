package com.xpertcaller.server.expertdata.controller;

import com.xpertcaller.server.expertdata.beans.AvailableTimeSlotRequest;
import com.xpertcaller.server.expertdata.beans.ExpertAvailableTimeSlots;
import com.xpertcaller.server.expertdata.beans.ExpertDetails;
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
}
