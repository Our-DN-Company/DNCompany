package com.example.dncompany.dto.help;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter@Setter@ToString
public class HelpDetailDTO {
    private Long helpId;
    private String helpTitle;
    private String helpCareType;
    private LocalDate helpCareDate;
    private LocalDateTime helpStartTime;
    private LocalDateTime helpEndTime;
    private String helpAddress;
    private String helpAddressDetail;
    private Integer helpPrice;
    private Integer helpPoint;
    private Long usersId;
    private String loginId;
    private LocalDateTime helpCreatedAt;
    private String helpSpecialNote;


    // 펫 정보 추가
    private Long petId;
    private String petName;
    private Integer petAge;
    private String petSpecies;
    private String petGender;

}
