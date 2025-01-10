package com.example.dncompany.dto.help;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;
import java.time.LocalDateTime;

// 도와주세요 DTO
@Getter @Setter @ToString
public class HelpWriteDTO {
    private Long helpId;
    private String helpTitle;
    private String helpCareType;
    private LocalDate helpCareDate;
    private String helpTime; // 화면 처리용
    private LocalDateTime helpStartTime; // 실제 DB 처리용
    private LocalDateTime helpEndTime; // 실제 DB 처리용
    private Integer helpPoint;
    private Integer helpPrice;
    private Long petId;
    private String helpSpecialNote;
    private String helpAddress;
    private String helpAddressDetail;
    private Long usersId;
}
