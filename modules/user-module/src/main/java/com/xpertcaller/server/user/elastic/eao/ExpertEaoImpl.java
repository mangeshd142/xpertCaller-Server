package com.xpertcaller.server.user.elastic.eao;

import com.xpertcaller.server.user.elastic.eao.interfaces.ExpertEao;
import com.xpertcaller.server.user.elastic.entities.Expert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ExpertEaoImpl implements ExpertEao {
    /*@Autowired
    ExpertRepository expertRepository;*/

    @Override
    public Expert insertExperts(Expert expert){
        return null;// expertRepository.save(expert);
    }
}
