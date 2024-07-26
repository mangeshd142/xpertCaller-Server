package com.xpertcaller.server.user.db.sql.entities.profileEntities;

import jakarta.persistence.*;
import lombok.*;

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

    @ElementCollection
    @CollectionTable(name = "category_names", joinColumns = @JoinColumn(name = "category_id"))
    @Column(name = "category_name")
    private List<String> category;
    private List<String> skills;

    public CategoryEntity(List<String> category, List<String> skills) {
        this.category = category;
        this.skills = skills;
    }
}
