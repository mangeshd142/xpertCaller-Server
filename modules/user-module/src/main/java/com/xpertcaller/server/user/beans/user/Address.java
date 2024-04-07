package com.xpertcaller.server.user.beans.user;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Address implements Serializable {
    private String id;
    private String street;
    private String city;
    private String state;
    private String zipCode;
    private String longitude;
    private String latitude;
}
