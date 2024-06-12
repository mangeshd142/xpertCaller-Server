package com.xpertcaller.server.user.controller;


import com.xpertcaller.server.common.exception.BusinessException;
import com.xpertcaller.server.user.beans.user.*;
import com.xpertcaller.server.user.db.sql.entities.profileEntities.AvailableTimeSlotChunksEntity;
import com.xpertcaller.server.user.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/addProfileDetails", method = RequestMethod.POST)
    public ProfileDetails addProfileDetails(@RequestBody ProfileDetails profileDetails) throws BusinessException {
        return userService.addProfileDetails(profileDetails);
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/addAvailableTimeSlots", method = RequestMethod.POST)
    public List<AvailableTimeSlot> addAvailableTimeSlots(@RequestBody Map<String, List<AvailableTimeSlot>> availableTimeSlotMap) throws BusinessException {
        return userService.addAvailableTimeSlots(availableTimeSlotMap.get("availableTimeSlots"));
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/fetchCurrentUser", method = RequestMethod.GET)
    public User fetchCurrentUser() throws BusinessException {
        return userService.fetchCurrentUser();
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/getAllTimeSlots", method = RequestMethod.GET)
    public List<AvailableTimeSlot> getAllTimeSlots() throws BusinessException {
        return userService.getAllTimeSlots();
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/getAvailableTimeSlotsByDate", method = RequestMethod.POST)
    public List<AvailableTimeSlot> getAvailableTimeSlotsByDate(@RequestBody AvailableTimeSlotRequest availableTimeSlotRequest) throws BusinessException {
        return userService.getAvailableTimeSlotsByDate(availableTimeSlotRequest);
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/getTimeSlotChunksByTimeslotId/{timeSlotId}", method = RequestMethod.GET)
    public List<AvailableTimeSlotChunks> getTimeSlotChunksByTimeslotId(@PathVariable String timeSlotId) throws BusinessException {
        return userService.getAvailableTimeslotChunksByTimeSlotId(timeSlotId);
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/updateUser", method = RequestMethod.POST)
    public User updateUser(@RequestBody User user) throws BusinessException {
        return userService.updateUser(user);
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/fetchProfileDetails", method = RequestMethod.GET)
    public ProfileDetails fetchProfileDetails() throws BusinessException {
        return userService.fetchProfileDetails();
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/deleteDocument/{fileName}", method = RequestMethod.GET)
    public String deleteDocument(@PathVariable String fileName ) throws BusinessException {
        return userService.deleteDocument(fileName);
    }

    @CrossOrigin(origins = "*")
    @RequestMapping("/createUsers")
    public List<UserProfileRequest> createUsers(@RequestBody Map<String, List<UserProfileRequest> > userProfileRequestMap) {
        return userService.createUsers(userProfileRequestMap);
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/getusers", method = RequestMethod.GET)
    public List<User> getUsers() throws BusinessException {
        return userService.getAllUsers();
    }
}
