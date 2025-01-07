package com.example.dncompany.controller.user;

import com.example.dncompany.dto.user.schedule.ScheduleListDTO;
import com.example.dncompany.service.user.schedule.ScheduleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/schedule")
@RequiredArgsConstructor
public class ScheduleController {
    private final ScheduleService scheduleService;

    @GetMapping("/calender")
    public String calender(@SessionAttribute(value = "usersId", required = false) Long userId, Model model) {
        userId = 6L;

        List<ScheduleListDTO> scheduleList = scheduleService.getScheduleList(userId);
        model.addAttribute("scheduleList", scheduleList);
        model.addAttribute("today", LocalDate.now());


        return "user/schedule/schedule";
    }
}
