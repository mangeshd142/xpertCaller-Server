package com.company.consultant.elastic.eao;

import com.company.consultant.elastic.eao.interfaces.ExpertEao;
import com.company.consultant.elastic.entities.Expert;
import com.company.consultant.elastic.repositories.ExpertRepository;
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
