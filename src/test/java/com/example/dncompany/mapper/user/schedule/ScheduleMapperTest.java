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
import java.util.List;

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
//        scheduleDTO.setHelpTitle("test");
//        scheduleDTO.setHelpCareDate(LocalDate.parse("2024-01-01"));
//        scheduleDTO.setHelpStartTime(LocalTime.parse("10:30"));
//        scheduleDTO.setHelpEndTime(LocalTime.parse("12:30"));
//        scheduleDTO.setUsersId(1L);
//        scheduleDTO.setHelpId(1L);
    }

    @Test
    @DisplayName("유저 아이디로 스케쥴 조회하기")
    void selectScheduleByUserId() {
        // given
        scheduleDTO.setUsersId(6L); // 테스트 데이터 준비

        // when
        List<ScheduleListDTO> schedules = scheduleMapper.selectScheduleByUserId(scheduleDTO.getUsersId());

        // then
        // 결과 리스트가 비어 있지 않음 확인
        // 결과가 null이 아님을 확인
        assertThat(schedules).isNotNull()
                             .isNotEmpty()
                             .extracting("helpTitle")
                             .contains("강아지 돌봄 부탁드려요");
    }
}