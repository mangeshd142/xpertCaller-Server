package com.company.consultant.controllers;

import com.company.consultant.moduls.AddCategory;
import com.company.consultant.moduls.User;
import com.company.consultant.service.interfaces.UserService;
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
