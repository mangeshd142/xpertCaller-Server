package com.xpertcaller.server.db.sql.entities;

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
@Table(name = "review_rating_table")
public class ReviewRating {
    @Id
    private String reviewId;
    private String reviewer;
    private String review;
    private float rating;
}
