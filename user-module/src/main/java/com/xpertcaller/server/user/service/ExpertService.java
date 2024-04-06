package com.xpertcaller.server.user.service;

import com.xpertcaller.server.user.elastic.eao.interfaces.ExpertEao;
import com.xpertcaller.server.user.elastic.entities.Expert;
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
