package com.example.dncompany.service.admin;


import com.example.dncompany.dto.admin.main.AdminBoardCountDTO;
import com.example.dncompany.dto.admin.main.AdminCardCountDTO;
import com.example.dncompany.dto.admin.main.AdminCardLastWeekDTO;
import com.example.dncompany.dto.admin.main.AdminUserCountDTO;
import com.example.dncompany.mapper.admin.AdminMainMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public Optional<AdminCardCountDTO> getDailyCardCounts() { return adminMainMapper.getCardBoardCounts();}

    public Optional<AdminCardLastWeekDTO> getDailyCardLastWeek() {return adminMainMapper.getLastWeekCardBoardCounts();}

}
