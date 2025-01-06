package com.example.dncompany.dto.user.schedule;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalTime;

@Setter @Getter @ToString
public class ScheduleListDTO {
    String helpTitle;
    LocalDate helpCareDate;
    LocalTime helpStartTime;
    LocalTime helpEndTime;
    Long usersId;
    Long helpId;
}
