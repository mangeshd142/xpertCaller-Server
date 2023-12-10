package com.company.consultant.service.interfaces;

import com.company.consultant.moduls.ConsultationCategory;
import com.company.consultant.moduls.ConsultationSkills;
import org.json.JSONException;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface ConsultationService {
    Map<String, Object> insertConsultationCategory() throws IOException, JSONException;
    List<ConsultationCategory> getAllConsultationCategory();
    List<ConsultationSkills> getConsultationSkillsByParentId(String parentId);
}
