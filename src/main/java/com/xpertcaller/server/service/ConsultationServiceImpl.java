package com.xpertcaller.server.service;

import com.xpertcaller.server.db.interfaces.dao.ConsultationDao;
import com.xpertcaller.server.db.sql.entities.ConsultationCategoryEntity;
import com.xpertcaller.server.db.sql.entities.ConsultationSkillsEntity;
import com.xpertcaller.server.beans.ConsultationCategory;
import com.xpertcaller.server.beans.ConsultationSkills;
import com.xpertcaller.server.service.interfaces.ConsultationService;
import com.xpertcaller.server.util.ReadResourceFile;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.*;

@Component
public class ConsultationServiceImpl implements ConsultationService {

    @Autowired
    ConsultationDao consultationDao;
    @Autowired
    ReadResourceFile readResourceFile;

    @Override
    public Map<String, Object> insertConsultationCategory() throws IOException, JSONException {

        String colleges = readResourceFile.readFileFromResources("consultationCategory.json");

        JSONObject jsonObject = new JSONObject(colleges);
        JSONArray jsonArray = jsonObject.getJSONArray("consultationCategory");
        List<ConsultationCategoryEntity> consultationCategoryList = new ArrayList<>();
        List<ConsultationSkillsEntity> consultationSkillsEntityList = new ArrayList<>();

        for (int i = 0; i < jsonArray.length(); i++){
            JSONObject consultationCategory = jsonArray.getJSONObject(i);
            String consultationId = consultationCategory.getString("consultationId");
            consultationCategoryList.add(new ConsultationCategoryEntity(consultationId, consultationCategory.getString("consultationName"),
                    consultationCategory.getString("consultationDescription"), consultationCategory.getString("image")));

            JSONArray skillSets = consultationCategory.getJSONArray("skillSets");
            for (int j = 0; j < skillSets.length(); j++){
                JSONObject skillSet = skillSets.getJSONObject(i);
                consultationSkillsEntityList.add(new ConsultationSkillsEntity(UUID.randomUUID().toString(), skillSet.getString("skillName"),
                        skillSet.getString("skillSetDescription"), consultationId));
            }
        }

        Map<String, Object> consultationCategories = new HashMap<>();
        List<ConsultationCategory> consultationCategoryList1 = new ArrayList<>();
        List<ConsultationSkills> consultationSkillsList = new ArrayList<>();

        List<ConsultationCategoryEntity> insertedConsultationCategoryEntity = consultationDao.insertConsultationCategories(consultationCategoryList);
        List<ConsultationSkillsEntity> insertedConsultationSkillsEntity = consultationDao.insertConsultationSkills(consultationSkillsEntityList);

        for (ConsultationCategoryEntity consultationCategory : insertedConsultationCategoryEntity){
            consultationCategoryList1.add(new ConsultationCategory(consultationCategory.getConsultationId(), consultationCategory.getConsultationName(),
                    consultationCategory.getConsultationDescription(), consultationCategory.getImage()));
        }

        for (ConsultationSkillsEntity consultationSkills : insertedConsultationSkillsEntity){
            consultationSkillsList.add(new ConsultationSkills(consultationSkills.getConsultationSkillId(), consultationSkills.getConsultationSkillName(),
                    consultationSkills.getConsultationSkillDescription(), consultationSkills.getParentId()));
        }

        consultationCategories.put("consultationCategory", consultationCategoryList1);
        consultationCategories.put("consultationSkills", consultationSkillsList);
        return consultationCategories;
    }

    @Override
    public List<ConsultationCategory> getAllConsultationCategory(){
        List<ConsultationCategoryEntity> consultationCategoryEntityList = consultationDao.getAllConsultationCategory();
        List<ConsultationCategory> consultationCategoryList = new ArrayList<>();
        for (ConsultationCategoryEntity consultationCategory : consultationCategoryEntityList){
            consultationCategoryList.add(new ConsultationCategory(consultationCategory.getConsultationId(), consultationCategory.getConsultationName(),
                    consultationCategory.getConsultationDescription(), consultationCategory.getImage()));
        }
        return  consultationCategoryList;
    }

    @Override
    public List<ConsultationSkills> getConsultationSkillsByParentId(String parentId){
        List<ConsultationSkillsEntity> consultationSkillsEntityList = consultationDao.getConsultationSkillsByParentId(parentId);
        List<ConsultationSkills> consultationSkillsList = new ArrayList<>();
        for (ConsultationSkillsEntity consultationSkills : consultationSkillsEntityList){
            consultationSkillsList.add(new ConsultationSkills(consultationSkills.getConsultationSkillId(), consultationSkills.getConsultationSkillName(),
                    consultationSkills.getConsultationSkillDescription(), consultationSkills.getParentId()));
        }
        return  consultationSkillsList;
    }

}
