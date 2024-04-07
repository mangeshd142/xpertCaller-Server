package com.xpertcaller.server.user.beans.user;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Category {
    private String id;
    private List<String> category;
    private List<String> skills;
}
