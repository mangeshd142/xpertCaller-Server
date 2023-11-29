package com.company.consultant.db.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "category_table")
public class Category {
    @Id
    private String categoryId;
    private String type;
    private String description;
}
