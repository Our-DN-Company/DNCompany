package com.example.dncompany.service.user.schedule;

import com.example.dncompany.dto.user.schedule.ScheduleListDTO;
import com.example.dncompany.mapper.user.schedule.ScheduleMapper;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.SessionAttribute;

import java.util.List;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class ScheduleService {
    private final ScheduleMapper scheduleMapper;

    public List<ScheduleListDTO> getScheduleList(Long userId) {


        return scheduleMapper.selectScheduleByUserId(userId);
    }

}
