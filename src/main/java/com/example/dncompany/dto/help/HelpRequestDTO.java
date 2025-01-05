package com.example.dncompany.dto.help;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter@Setter@ToString
public class HelpRequestDTO {
   private Long helpId;
   private String helpTitle;
   private String helpSpecies;
   private String helpCareType;
   private LocalDate helpCareDate;
   private LocalDateTime helpStartTime;
   private LocalDateTime helpEndTime;
   private String helpAddress;
   private String helpAddressDetail;
   private Integer helpPrice;
   private Integer helpPoint;
}
