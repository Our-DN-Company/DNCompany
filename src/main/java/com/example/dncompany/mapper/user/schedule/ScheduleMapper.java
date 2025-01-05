package com.example.dncompany.mapper.user.schedule;

import com.example.dncompany.dto.user.schedule.ScheduleDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ScheduleMapper {

    ScheduleDTO selectScheduleByUserId(Long usersId);
}
