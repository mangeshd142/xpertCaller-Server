package com.xpertcaller.server.controllers;

import com.xpertcaller.server.elastic.entities.Expert;
import com.xpertcaller.server.moduls.College;
import com.xpertcaller.server.moduls.ConsultationCategory;
import com.xpertcaller.server.moduls.ConsultationSkills;
import com.xpertcaller.server.service.ExpertService;
import com.xpertcaller.server.service.interfaces.CollegeService;
import com.xpertcaller.server.service.interfaces.ConsultationService;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/service")
public class ServiceController {

    @Autowired
    CollegeService collegeService;

    @Autowired
    ConsultationService consultationService;

    @Autowired
    ExpertService expertService;

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
    @CrossOrigin(origins = "*")
    @RequestMapping("/add-consultation-category")
    public Map<String, Object> addConsultationCategory() throws IOException, JSONException {
        return consultationService.insertConsultationCategory();
    }

    @CrossOrigin(origins = "*")
    @RequestMapping("/get-consultation-category")
    public List<ConsultationCategory> getConsultationCategories() {
        return consultationService.getAllConsultationCategory();
    }

    @CrossOrigin(origins = "*")
    @RequestMapping("/get-consultation-skills")
    public List<ConsultationSkills> getConsultationCategories(@RequestBody String parentId) {
        return consultationService.getConsultationSkillsByParentId(parentId);
    }

    @CrossOrigin(origins = "*")
    @RequestMapping("/create-data")
    public void createData() throws JSONException, IOException {
        collegeService.insertColleges();
        consultationService.insertConsultationCategory();
        return ;
    }

    @CrossOrigin(origins = "*")
    @RequestMapping("/expert")
    public Expert insertExpert(Expert expert) throws JSONException, IOException {
        return expertService.insertExpert(expert);
    }
}
