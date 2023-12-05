package com.company.consultant.controllers;

import com.company.consultant.db.entities.User;
import com.company.consultant.moduls.College;
import com.company.consultant.service.interfaces.CollegeServiceInterface;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/college")
public class CollegeController {

    @Autowired
    CollegeServiceInterface collegeService;

    @RequestMapping("/addColleges")
    public List<College> addColleges() throws IOException, JSONException {
        return null;//collegeService.insertColleges();
    }

    @RequestMapping("/getColleges")
    public List<College> getColleges(@RequestBody String collegeName) throws IOException, JSONException {
        return collegeService.getColleges(collegeName);
    }
}
