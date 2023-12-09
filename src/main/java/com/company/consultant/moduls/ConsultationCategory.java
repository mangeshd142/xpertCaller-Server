package com.company.consultant.moduls;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ConsultationCategory {
    private String consultationId;
    private String consultationName;
    private String consultationDescription;
    private String image;
}
