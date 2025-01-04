package com.example.dncompany.service.admin;

import com.example.dncompany.dto.admin.user.board.AdminUserAllBoard;
import com.example.dncompany.mapper.admin.AdminUserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminUserService {

    private final AdminUserMapper adminUserMapper;

    public List<AdminUserAllBoard> getAllUserData(AdminUserAllBoard searchCriteria) {
        return adminUserMapper.selectUserData();
    }
}
