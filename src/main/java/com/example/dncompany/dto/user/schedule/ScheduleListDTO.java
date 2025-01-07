package com.example.dncompany.dto.user.schedule;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalTime;

@Setter @Getter @ToString
public class ScheduleListDTO {
    private String helpTitle;
    private LocalDate helpCareDate;
    private LocalTime helpStartTime;
    private LocalTime helpEndTime;
    private Long usersId;
    private Long helpId;
    private String helpCareType;
}
