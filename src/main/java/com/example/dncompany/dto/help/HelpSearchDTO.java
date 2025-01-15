package com.example.dncompany.dto.help;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter@Setter@ToString
public class HelpSearchDTO {
    private String sido;
    private String gugun;
    private LocalDate careDate;
    private String careType;
    private String keyword;

}
