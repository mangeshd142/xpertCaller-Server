package com.xpertcaller.server.controllers;

import com.xpertcaller.server.aws.service.S3BucketService;
import com.xpertcaller.server.elastic.entities.Expert;
import com.xpertcaller.server.beans.user.College;
import com.xpertcaller.server.beans.ConsultationCategory;
import com.xpertcaller.server.beans.ConsultationSkills;
import com.xpertcaller.server.service.ExpertService;
import com.xpertcaller.server.service.interfaces.CollegeService;
import com.xpertcaller.server.service.interfaces.ConsultationService;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/service")
public class ServiceController {

    @Autowired
    CollegeService collegeService;

    @Autowired
    ConsultationService consultationService;

    @Autowired
    ExpertService expertService;

    @Autowired
    S3BucketService s3BucketService;

    @CrossOrigin(origins = "*")
    @RequestMapping("/addData")
    public List<College> addColleges() throws IOException, JSONException {
        return collegeService.insertColleges();
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/getColleges", method = RequestMethod.GET)
    public List<College> getColleges(@RequestParam String collegeName) throws IOException, JSONException {
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
    @RequestMapping(value = "/create-data", method = RequestMethod.GET)
    public void createData() throws JSONException, IOException {
        collegeService.insertColleges();
        consultationService.insertConsultationCategory();
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/expert", method = RequestMethod.POST)
    public Expert insertExpert(Expert expert) throws JSONException, IOException {
        return expertService.insertExpert(expert);
    }
    @CrossOrigin(origins = "*")
    @RequestMapping("/uploadFile")
    public String uploadFile(@RequestParam(name = "file") MultipartFile multipartFile) throws JSONException, IOException {
        File file = File.createTempFile("temp", null);
        multipartFile.transferTo(file);
        s3BucketService.uploadFile(UUID.randomUUID().toString(), file);
        return "Success";
    }
}
