package com.xpertcaller.server.elastic.eao;

import com.xpertcaller.server.elastic.eao.interfaces.ExpertEao;
import com.xpertcaller.server.elastic.entities.Expert;
import com.xpertcaller.server.elastic.repositories.ExpertRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ExpertEaoImpl implements ExpertEao {
    @Autowired
    ExpertRepository expertRepository;

    @Override
    public Expert insertExperts(Expert expert){
        return expertRepository.save(expert);
    }
}
