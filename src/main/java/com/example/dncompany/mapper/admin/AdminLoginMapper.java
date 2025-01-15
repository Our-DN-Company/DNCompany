package com.example.dncompany.mapper.admin;


import com.example.dncompany.dto.admin.login.AdminLoginDTO;
import com.example.dncompany.dto.admin.login.AdminSessionDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.Optional;

@Mapper
public interface AdminLoginMapper {

    Optional<AdminSessionDTO> selectAdminLoginInfo(AdminLoginDTO adminLogin);

}
