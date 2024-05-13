package com.xpertcaller.server.user.db.sql.entities.profileEntities;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "pricing_table")
public class PricingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String pricingId;
    private float videoCalling;
    private float audioCalling;
    private float message;

    public PricingEntity(float videoCalling, float audioCalling, float message){
        this.videoCalling = videoCalling;
        this.audioCalling = audioCalling;
        this.message = message;
    }
}
