package com.xpertcaller.server.elastic.repositories;

import com.xpertcaller.server.elastic.entities.Expert;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExpertRepository extends ElasticsearchRepository<Expert, String> {
}
