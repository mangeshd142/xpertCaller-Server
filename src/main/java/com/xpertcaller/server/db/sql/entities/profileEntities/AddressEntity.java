package com.xpertcaller.server.db.sql.entities.profileEntities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import lombok.*;
import jakarta.persistence.Id;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "address_table")
public class AddressEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String addressId;
    private String street;
    private String city;
    private String state;
    private String zipCode;
    private String longitude;
    private String latitude;
    public AddressEntity(String street, String city, String state, String zipCode, String longitude, String latitude) {
        this.street = street;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
        this.longitude = longitude;
        this.latitude = latitude;
    }
}
