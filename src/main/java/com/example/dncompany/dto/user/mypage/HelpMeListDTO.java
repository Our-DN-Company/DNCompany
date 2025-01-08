package com.example.dncompany.dto.user.mypage;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter @Setter @ToString
public class HelpMeListDTO {
    Long helpId;
    String helpTitle;
    String helpCareType;
    LocalDate helpCareDate;
    LocalDate helpCreatedAt;
    Long userId;
}
