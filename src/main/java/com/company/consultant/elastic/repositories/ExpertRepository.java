package com.company.consultant.elastic.repositories;

import com.company.consultant.elastic.entities.Expert;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface ExpertRepository extends ElasticsearchRepository<Expert, String> {
}
