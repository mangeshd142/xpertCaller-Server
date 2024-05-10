package com.xpertcaller.server.expertdata.bo;

import com.xpertcaller.server.expertdata.beans.ExpertDetails;

import java.util.List;

public interface ExpertDetailBo {
    ExpertDetails fetchExpertDetails(String id);

    List<ExpertDetails> fetchAllExpertDetails();
}
