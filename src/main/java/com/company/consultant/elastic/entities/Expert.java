package com.company.consultant.elastic.entities;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Document(indexName = "expert")
public class Expert {
    @Id
    private String expertId;
    private String name;
    private String tags;
    private String category;
    private String detailedInfo;
    private String searchKeyword;
}
