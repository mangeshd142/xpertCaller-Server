package com.xpertcaller.server.user.db.sql.entities.profileEntities;

import jakarta.persistence.*;
import lombok.*;

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
