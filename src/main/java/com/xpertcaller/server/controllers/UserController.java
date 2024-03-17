package com.xpertcaller.server.controllers;

import com.xpertcaller.server.exception.userdefined.BusinessException;
import com.xpertcaller.server.moduls.user.AddCategory;
import com.xpertcaller.server.moduls.user.User;
import com.xpertcaller.server.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/addCategory", method = RequestMethod.POST)
    public User addCategory(@RequestBody AddCategory addCategory) throws BusinessException {
        return userService.addCategory(addCategory);
    }
}
