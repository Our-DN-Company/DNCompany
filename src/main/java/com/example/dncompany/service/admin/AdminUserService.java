package com.example.dncompany.service.admin;

import com.example.dncompany.dto.admin.user.AdmInUserReportDTO;
import com.example.dncompany.dto.admin.user.board.AdminUserAllBoard;
import com.example.dncompany.mapper.admin.AdminUserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminUserService {

    private final AdminUserMapper adminUserMapper;

    public List<AdminUserAllBoard> getAllUserData(AdminUserAllBoard searchCriteria) {
        // 검색 조건 전처리가 필요한 경우 여기서 처리
        if (searchCriteria.getSearchPhoneNumber() != null) {
            // 전화번호에서 하이픈 제거
            searchCriteria.setSearchPhoneNumber(
                    searchCriteria.getSearchPhoneNumber().replaceAll("-", "")
            );
        }
        return adminUserMapper.selectUserData(searchCriteria);
    }

    public List<AdmInUserReportDTO> getReportsByUserId(Long userId) {
        return adminUserMapper.getReportsByUserId(userId);
    }

    public boolean updateReportStatus(Long reportId, String status) {
        return adminUserMapper.updateReportStatus(reportId, status) > 0;
    }

    @Transactional
    public boolean updateUserPoints(Long userId, Integer points) {
        return adminUserMapper.updateUserPoints(userId, points) > 0;
    }

    @Transactional
    public boolean banUser(Long userId, Integer banDays) {
        return adminUserMapper.updateUserBanStatus(userId, banDays) > 0;
    }
}
