package com.company.consultant.controllers;

import com.company.consultant.db.entities.UserEntity;
import com.company.consultant.moduls.User;
import com.company.consultant.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class HomePageController {

    @Autowired
    UserService userService;

    @RequestMapping("/")
    public String sayHi() {
        return "Well-Come";
    }
    @RequestMapping("/about")
    public String about() {
        return "About";
    }
    @RequestMapping("/help")
    public String help() {
        return "Help";
    }

    @RequestMapping("/getusers")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

}
