package com.example.dncompany.mapper.admin;


import com.example.dncompany.dto.admin.main.AdminBoardCountDTO;
import com.example.dncompany.dto.admin.main.AdminUserCountDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AdminMainMapper {


    List<AdminBoardCountDTO> getDailyBoardCounts();

    List<AdminUserCountDTO> getDailyUserCounts();


}
