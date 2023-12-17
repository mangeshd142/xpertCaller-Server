package com.company.consultant.service;

import com.company.consultant.elastic.eao.interfaces.ExpertEao;
import com.company.consultant.elastic.entities.Expert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExpertService {
    @Autowired
    ExpertEao expertEao;

    public Expert insertExpert(Expert expert){
        return expertEao.insertExperts(expert);
    }
}
