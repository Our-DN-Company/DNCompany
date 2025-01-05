package com.example.dncompany.mapper.admin;

import com.example.dncompany.dto.admin.user.board.AdminUserAllBoard;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AdminUserMapper {
    List<AdminUserAllBoard> selectUserData();
}
