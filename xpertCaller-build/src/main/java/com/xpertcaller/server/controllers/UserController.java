package com.xpertcaller.server.controllers;


import com.xpertcaller.server.common.exception.BusinessException;
import com.xpertcaller.server.user.beans.user.ProfileDetails;
import com.xpertcaller.server.user.beans.user.User;
import com.xpertcaller.server.user.service.interfaces.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/addProfileDetails", method = RequestMethod.POST)
    public ProfileDetails addProfileDetails(@RequestBody ProfileDetails profileDetails) throws BusinessException {
        return userService.addProfileDetails(profileDetails);
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/fetchCurrentUser", method = RequestMethod.GET)
    public User fetchCurrentUser() throws BusinessException {
        return userService.fetchCurrentUser();
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
}
