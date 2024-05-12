package com.xpertcaller.server.expertdata.serviceimpl;

import com.xpertcaller.server.expertdata.beans.ExpertAvailableTimeSlots;
import com.xpertcaller.server.expertdata.beans.ExpertDetails;
import com.xpertcaller.server.expertdata.bo.ExpertDetailBo;
import com.xpertcaller.server.expertdata.service.ExpertDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExpertDetailServiceImpl implements ExpertDetailService {

    @Autowired
    ExpertDetailBo expertDetailBo;

    @Override
    public ExpertDetails fetchExpertDetails(String id) {
        return expertDetailBo.fetchExpertDetails(id);
    }

    @Override
    public List<ExpertDetails> fetchAllExpertDetails(){
        return expertDetailBo.fetchAllExpertDetails();
    }

    @Override
    public List<ExpertAvailableTimeSlots> fetchTimeSlotByUser(String userId, long startDateL, String zone){
        return expertDetailBo.fetchTimeSlotByUser(userId, startDateL, zone);
    }
}
