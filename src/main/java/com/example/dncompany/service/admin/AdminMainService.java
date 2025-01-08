package com.example.dncompany.service.admin;


import com.example.dncompany.dto.admin.main.AdminBoardCountDTO;
import com.example.dncompany.dto.admin.main.AdminUserCountDTO;
import com.example.dncompany.mapper.admin.AdminMainMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminMainService {

    private final AdminMainMapper adminMainMapper;

    public List<AdminBoardCountDTO> getDailyBoardCounts() {
        return adminMainMapper.getDailyBoardCounts();
    }

    public List<AdminUserCountDTO> getDailyUserCounts() {
        return adminMainMapper.getDailyUserCounts();
    }
}
