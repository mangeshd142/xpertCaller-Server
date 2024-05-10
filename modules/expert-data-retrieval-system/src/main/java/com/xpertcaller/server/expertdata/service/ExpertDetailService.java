package com.xpertcaller.server.expertdata.service;

import com.xpertcaller.server.expertdata.beans.ExpertDetails;

import java.util.List;

public interface ExpertDetailService {
    ExpertDetails fetchExpertDetails(String id);

    List<ExpertDetails> fetchAllExpertDetails();
}
