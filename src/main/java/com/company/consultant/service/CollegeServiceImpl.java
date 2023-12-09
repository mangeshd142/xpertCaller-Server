package com.company.consultant.service;

import com.company.consultant.db.dao.interfaces.CollegeDao;
import com.company.consultant.db.entities.CollegeEntity;
import com.company.consultant.moduls.College;
import com.company.consultant.service.interfaces.CollegeService;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class CollegeServiceImpl implements CollegeService {
    @Autowired
    CollegeDao collegeDao;

    @Override
    public List<College> insertColleges() throws IOException, JSONException {

        String colleges = readFileFromResources();

        JSONObject jsonObject = new JSONObject(colleges);
        JSONArray jsonArray = jsonObject.getJSONArray("colleges");
        List<CollegeEntity> collegeListEntity = new ArrayList<>();

        for (int i = 0; i < jsonArray.length(); i++){
            JSONObject college = jsonArray.getJSONObject(i);
            collegeListEntity.add(new CollegeEntity(UUID.randomUUID().toString(), college.getString("university"), college.getString("college"), college.getString("college_type"), college.getString("state"), college.getString("district")));
        }
        List<CollegeEntity> insertedCollegeListEntity = collegeDao.insertColleges(collegeListEntity);
        List<College> collegeList = new ArrayList<>();
        for (CollegeEntity college : insertedCollegeListEntity){
            collegeList.add(new College(college.getCollegeId(), college.getUniversity(), college.getCollege(), college.getCollegeType(), college.getState(), college.getDistrict()));
        }
        return collegeList;
    }

    @Override
    public List<College> getColleges(String collegeName) {
        List<CollegeEntity> collegeEntities = collegeDao.getColleges(collegeName);
        List<College> collegeList = new ArrayList<>();
        for (CollegeEntity college : collegeEntities){
            collegeList.add(new College(college.getCollegeId(), college.getUniversity(), college.getCollege(), college.getCollegeType(), college.getState(), college.getDistrict()));
        }
        return collegeList;
    }

    public String readFileFromResources() throws IOException {
        // Specify the file name or path within the resources directory
        String fileName = "colleges.json";

        // Create a ClassPathResource using the specified file name
        Resource resource = new ClassPathResource(fileName);

        // Open an InputStream from the resource
        try (InputStream inputStream = resource.getInputStream()) {
            // Read the contents of the file into a byte array
            byte[] bytes = FileCopyUtils.copyToByteArray(inputStream);

            // Convert the byte array to a String (assuming the file contains text)
            return new String(bytes, StandardCharsets.UTF_8);
        }
    }

}
