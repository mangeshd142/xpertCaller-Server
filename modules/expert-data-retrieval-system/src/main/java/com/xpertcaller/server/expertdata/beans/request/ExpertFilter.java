package com.xpertcaller.server.expertdata.beans.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ExpertFilter {
    String category;
    String gender;
    String sortByExperience;
    int page;
    int size;
}
