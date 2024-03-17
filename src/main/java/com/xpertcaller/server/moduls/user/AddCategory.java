package com.xpertcaller.server.moduls.user;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AddCategory {
    private String userId;
    private String category;
    private List<String> skills;
}
