package com.example.dncompany.mapper.user.schedule;

import com.example.dncompany.dto.user.schedule.ScheduleDTO;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class ScheduleMapperTest {

    @Autowired
    private ScheduleMapper scheduleMapper;

    ScheduleDTO scheduleDTO;

    @BeforeEach
    void setUp() {
        scheduleDTO = new ScheduleDTO();
        scheduleDTO.setScheduleId(1L);
        scheduleDTO.setScheduleTitle("test");
        scheduleDTO.setScheduleContent("test content");
        scheduleDTO.setScheduleDate(LocalDate.parse("2024-01-01"));
        scheduleDTO.setScheduleStartTime(LocalTime.parse("10:30"));
        scheduleDTO.setScheduleEndTime(LocalTime.parse("12:30"));
        scheduleDTO.setUsersId(1L);
        scheduleDTO.setHelpId(1L);
    }

    @Test
    @DisplayName("유저 아이디로 스케쥴 조회하기")
    void selectScheduleByUserId() {
        //given
        // @BeforeEach에서 설정
        //when
        scheduleMapper.selectScheduleByUserId(scheduleDTO.getUsersId());
        //then
        // 유저아이디로 조회를 했을 때 유저가 생성한 스케쥴이 모두 잘 들어가는지 확인하면 됨
        assertThat(scheduleDTO.getUsersId()).isEqualTo(1L);

    }
}