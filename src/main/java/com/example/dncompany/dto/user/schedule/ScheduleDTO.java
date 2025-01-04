package com.example.dncompany.dto.user.schedule;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Setter @Getter @ToString
public class ScheduleDTO {
    Long scheduleId;
    String scheduleTitle;
    String scheduleContent;
    LocalDate scheduleDate;
    LocalTime scheduleStartTime;
    LocalTime scheduleEndTime;
    Long usersId;
    Long helpId;
}
