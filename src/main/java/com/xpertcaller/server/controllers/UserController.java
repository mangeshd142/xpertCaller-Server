package com.xpertcaller.server.controllers;

import com.xpertcaller.server.moduls.AddCategory;
import com.xpertcaller.server.moduls.User;
import com.xpertcaller.server.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @CrossOrigin(origins = "*")
    @RequestMapping("/createUser")
    public User createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    @CrossOrigin(origins = "*")
    @RequestMapping("/addCategory")
    public void addCategory(@RequestBody AddCategory addCategory){

    }
}
