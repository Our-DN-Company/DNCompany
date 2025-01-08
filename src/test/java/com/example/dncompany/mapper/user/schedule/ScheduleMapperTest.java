package com.example.dncompany.mapper.user.schedule;

import com.example.dncompany.dto.user.schedule.ScheduleListDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class ScheduleMapperTest {

    @Autowired
    private ScheduleMapper scheduleMapper;

    ScheduleListDTO scheduleDTO;

    @BeforeEach
    void setUp() {
        scheduleDTO = new ScheduleListDTO();
        scheduleDTO.setHelpTitle("test");
        scheduleDTO.setHelpCareDate(LocalDate.parse("2024-01-01"));
        scheduleDTO.setHelpStartTime(LocalTime.parse("10:30"));
        scheduleDTO.setHelpEndTime(LocalTime.parse("12:30"));
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