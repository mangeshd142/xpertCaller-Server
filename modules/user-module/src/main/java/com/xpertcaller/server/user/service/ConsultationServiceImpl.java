package com.xpertcaller.server.user.service;

import com.xpertcaller.server.common.util.ReadResourceFile;
import com.xpertcaller.server.user.beans.ConsultationCategory;
import com.xpertcaller.server.user.beans.ConsultationSkills;
import com.xpertcaller.server.user.db.interfaces.dao.ConsultationDao;
import com.xpertcaller.server.user.db.sql.entities.ConsultationCategoryEntity;
import com.xpertcaller.server.user.db.sql.entities.ConsultationSkillsEntity;
import com.xpertcaller.server.user.service.interfaces.ConsultationService;
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
            ConsultationCategoryEntity consultationCategoryEntity = new ConsultationCategoryEntity();
            consultationCategoryEntity.setConsultationId(consultationId);
            consultationCategoryEntity.setConsultationName(consultationCategory.getString("consultationName"));
            consultationCategoryEntity.setConsultationDescription(consultationCategory.getString("consultationDescription"));
            consultationCategoryEntity.setImage(consultationCategory.getString("image"));

            JSONArray skillSets = consultationCategory.getJSONArray("skillSets");
            for (int j = 0; j < skillSets.length(); j++){
                try {
                    JSONObject skillSet = skillSets.getJSONObject(j);
                    ConsultationSkillsEntity consultationSkillsEntity = new ConsultationSkillsEntity(UUID.randomUUID().toString(), skillSet.getString("skillName"),
                            skillSet.getString("skillSetDescription"));
                    consultationSkillsEntity.setConsultationCategoryEntity(consultationCategoryEntity);
                    consultationSkillsEntityList.add(consultationSkillsEntity);
                } catch (JSONException e) {

                }
            }
            consultationCategoryEntity.setConsultationSkills(consultationSkillsEntityList);
            consultationCategoryList.add(consultationCategoryEntity);
        }

        Map<String, Object> consultationCategories = new HashMap<>();
        List<ConsultationCategory> consultationCategoryList1 = new ArrayList<>();

        List<ConsultationCategoryEntity> insertedConsultationCategoryEntity = consultationDao.insertConsultationCategories(consultationCategoryList);

        for (ConsultationCategoryEntity consultationCategory : insertedConsultationCategoryEntity){
            consultationCategoryList1.add(new ConsultationCategory(consultationCategory.getConsultationId(), consultationCategory.getConsultationName(),
                    consultationCategory.getConsultationDescription(), consultationCategory.getImage()));
        }

        consultationCategories.put("consultationCategory", consultationCategoryList1);
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
                    consultationSkills.getConsultationSkillDescription(), consultationSkills.getConsultationCategoryEntity().getConsultationId()));
        }
        return  consultationSkillsList;
    }

}
