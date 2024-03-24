package com.xpertcaller.server.controllers;

import com.xpertcaller.server.beans.user.ProfileDetails;
import com.xpertcaller.server.exception.userdefined.BusinessException;
import com.xpertcaller.server.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/addProfileDetails", method = RequestMethod.POST)
    public ProfileDetails addProfileDetails(@RequestBody ProfileDetails profileDetails) throws BusinessException {
        return userService.addProfileDetails(profileDetails);
    }
}
