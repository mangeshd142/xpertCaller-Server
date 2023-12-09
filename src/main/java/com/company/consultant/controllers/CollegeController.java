package com.company.consultant.controllers;

import com.company.consultant.moduls.College;
import com.company.consultant.service.interfaces.CollegeService;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/service")
public class CollegeController {

    @Autowired
    CollegeService collegeService;

    @CrossOrigin(origins = "*")
    @RequestMapping("/addData")
    public List<College> addColleges() throws IOException, JSONException {
        return collegeService.insertColleges();
    }

    @CrossOrigin(origins = "*")
    @RequestMapping("/getColleges")
    public List<College> getColleges(@RequestBody String collegeName) throws IOException, JSONException {
        return collegeService.getColleges(collegeName);
    }
}
