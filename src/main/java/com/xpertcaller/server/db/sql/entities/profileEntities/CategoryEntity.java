package com.xpertcaller.server.db.sql.entities.profileEntities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import lombok.*;
import jakarta.persistence.Id;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "category_table")
public class CategoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String categoryId;
    private String category;
    private List<String> skills;

    public CategoryEntity(String category, List<String> skills) {
        this.category = category;
        this.skills = skills;
    }
}
