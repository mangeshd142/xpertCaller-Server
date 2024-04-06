package com.xpertcaller.server.config.beans;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ErrorResponseBean {
    private String errorMessage;
    private String errorCode;
    private String httpStatusCode;
}
