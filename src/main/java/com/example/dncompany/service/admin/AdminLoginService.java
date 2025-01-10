package com.example.dncompany.service.admin;

import com.example.dncompany.dto.admin.login.AdminLoginDTO;
import com.example.dncompany.dto.admin.login.AdminSessionDTO;
import com.example.dncompany.exception.user.LoginFailedException;
import com.example.dncompany.mapper.admin.AdminLoginMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminLoginService {

    private final AdminLoginMapper adminLoginMapper;

    public AdminSessionDTO getAdminLoginInfo(AdminLoginDTO adminLoginDTO) {

        return adminLoginMapper.selectAdminLoginInfo(adminLoginDTO)
                .orElseThrow(() -> new LoginFailedException("Login Failed"));
    }


}
