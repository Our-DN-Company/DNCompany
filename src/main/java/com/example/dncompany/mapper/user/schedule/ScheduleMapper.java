package com.example.dncompany.mapper.user.schedule;

import com.example.dncompany.dto.user.schedule.ScheduleListDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ScheduleMapper {

    List<ScheduleListDTO> selectScheduleByUserId(Long usersId);
}
