package com.company.consultant.elastic.repositories;

import com.company.consultant.elastic.entities.Expert;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExpertRepository extends ElasticsearchRepository<Expert, String> {
}
