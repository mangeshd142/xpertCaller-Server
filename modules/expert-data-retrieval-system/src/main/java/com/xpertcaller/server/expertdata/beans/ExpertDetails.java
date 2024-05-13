package com.xpertcaller.server.expertdata.beans;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ExpertDetails {
    private String id;
    private String name;
    private String gender;
    private String overallRating;
    private String about;
    private String city;
    private List<String> languages;
    private List<String> category;
    private List<String> skills;
    private List<String> degree;
    private float experience;
    private List<String> files;
    private Pricing pricing;
}
