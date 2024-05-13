package com.xpertcaller.server.expertdata.beans;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Pricing {
    private float videoCalling;
    private float audioCalling;
    private float message;
}
