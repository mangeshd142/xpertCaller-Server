package com.company.consultant.service;

import com.company.consultant.db.dao.interfaces.ConsultationDao;
import com.company.consultant.db.entities.ConsultationCategoryEntity;
import com.company.consultant.db.entities.ConsultationSkillsEntity;
import com.company.consultant.moduls.ConsultationCategory;
import com.company.consultant.moduls.ConsultationSkills;
import com.company.consultant.service.interfaces.ConsultationService;
import com.company.consultant.util.ReadResourceFile;
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
