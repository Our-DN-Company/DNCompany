package com.example.dncompany.mapper.admin;

import com.example.dncompany.dto.admin.user.AdmInUserReportDTO;
import com.example.dncompany.dto.admin.user.board.AdminUserAllBoard;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface AdminUserMapper {
    // 회원 목록 조회 - 검색 조건 파라미터 추가
    List<AdminUserAllBoard> selectUserData(AdminUserAllBoard searchCriteria);
    //  페이징처리를 위한 카운트 메서드
    int countUserData(AdminUserAllBoard searchCriteria);
    // 특정 회원의 신고 내역 조회
    List<AdmInUserReportDTO> getReportsByUserId(Long userId);




    // 신고 상태 업데이트
    int updateReportStatus(@Param("reportId") Long reportId, @Param("status") String status);

    // 포인트 수정
    int updateUserPoints(@Param("userId") Long userId, @Param("points") Integer points);

    // 활동 정지 상태 업데이트
    int updateUserBanStatus(@Param("userId") Long userId, @Param("banDays") Integer banDays);

    int getCurrentUserPoints(@Param("userId") Long userId);
}