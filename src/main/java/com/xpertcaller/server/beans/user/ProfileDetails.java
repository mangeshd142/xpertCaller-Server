package com.xpertcaller.server.beans.user;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ProfileDetails {
    private String profilePic;
    private String about;
    private List<String> languages;
    private List<EducationDetails> educationDetails;
    private Category category;
    private List<Experience> experiences;
    private Address address;
    private List<String> files;
    private List<String> deleteEducationDetailIds;
    private List<String> deleteExperienceIds;
}
