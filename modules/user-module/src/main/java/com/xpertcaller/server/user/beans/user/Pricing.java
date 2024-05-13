package com.xpertcaller.server.user.beans.user;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Pricing {
    private String pricingId;
    private float videoCalling;
    private float audioCalling;
    private float message;
}
