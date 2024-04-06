package com.xpertcaller.server.user.service.interfaces;

import com.xpertcaller.server.user.beans.ConsultationCategory;
import com.xpertcaller.server.user.beans.ConsultationSkills;
import org.json.JSONException;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface ConsultationService {
    Map<String, Object> insertConsultationCategory() throws IOException, JSONException;
    List<ConsultationCategory> getAllConsultationCategory();
    List<ConsultationSkills> getConsultationSkillsByParentId(String parentId);
}
