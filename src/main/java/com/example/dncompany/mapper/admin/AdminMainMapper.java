package com.example.dncompany.mapper.admin;


import com.example.dncompany.dto.admin.main.AdminBoardCountDTO;
import com.example.dncompany.dto.admin.main.AdminCardCountDTO;
import com.example.dncompany.dto.admin.main.AdminCardLastWeekDTO;
import com.example.dncompany.dto.admin.main.AdminUserCountDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface AdminMainMapper {


    List<AdminBoardCountDTO> getDailyBoardCounts();

    List<AdminUserCountDTO> getDailyUserCounts();

    Optional<AdminCardCountDTO> getCardBoardCounts();

    Optional<AdminCardLastWeekDTO> getLastWeekCardBoardCounts();


}
