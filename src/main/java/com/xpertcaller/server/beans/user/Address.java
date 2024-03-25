package com.xpertcaller.server.beans.user;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Address {
    private String addressId;
    private String street;
    private String city;
    private String state;
    private String zipCode;
    private String longitude;
    private String latitude;
}
