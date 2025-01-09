package com.example.dncompany.controller.user.schdule;

import com.example.dncompany.dto.user.schedule.ScheduleListDTO;
import com.example.dncompany.service.user.schedule.ScheduleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ScheduleListApi {
    private final ScheduleService scheduleService;

    @GetMapping("/v1/users/schedules/{usersId}")
    public List<ScheduleListDTO> getUserSchedules(@PathVariable("usersId") Long usersId
                                                  ) {
//        @SessionAttribute(value = "usersId", required = false) Long userSessionId
        usersId = 6L;
//        usersId = userSessionId;
        return scheduleService.getScheduleList(usersId);
    }

    @GetMapping("/v1/users/schedules/dates/{dateStr}")
    public List<ScheduleListDTO> getDayUserSchedules(@PathVariable("dateStr") LocalDate dateStr,
                                                     @SessionAttribute(value = "usersId", required = false) Long usersId){
        usersId = 6L;
        return scheduleService.getScheduleDayList(dateStr, usersId);
    }


}
