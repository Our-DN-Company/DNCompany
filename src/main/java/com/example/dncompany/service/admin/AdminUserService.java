package com.example.dncompany.service.admin;

import com.example.dncompany.dto.admin.user.AdmInUserReportDTO;
import com.example.dncompany.dto.admin.user.board.AdminUserAllBoard;
import com.example.dncompany.mapper.admin.AdminUserMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class AdminUserService {

    private final AdminUserMapper adminUserMapper;

    public List<AdminUserAllBoard> getAllUserData(AdminUserAllBoard searchCriteria) {
        // 페이징 오프셋 계산
        int offset = (searchCriteria.getPage() - 1) * searchCriteria.getSize();
        searchCriteria.setOffset(offset);

        return adminUserMapper.selectUserData(searchCriteria);
    }

    public int getTotalUserCount(AdminUserAllBoard searchCriteria) {
        return adminUserMapper.countUserData(searchCriteria);
    }




    public List<AdmInUserReportDTO> getReportsByUserId(Long userId) {
        return adminUserMapper.getReportsByUserId(userId);
    }

//        @TODO 안쓰는 코드 삭제 예정
//    public boolean updateReportStatus(Long reportId, String status) {
//        return adminUserMapper.updateReportStatus(reportId, status) > 0;
//    }


    // 포인트 변경은 트랜잭션으로 처리되어야 함 why? 어디 하나 오류가 발생하면 포인트 복사버그가 될 수 있음
    // 이미 만들어서 어쩔 수 없지만 다음에는 포인트 정보를 담을 DTO를 만들 예정
    // 일딴 Map<String, Object> 특히 오브젝트 떄문에 불안함 포인트
    // 입력칸을 숫자로 한정해서 당장 생각나는 큰 문제는 생각나지 않지만
    // 짱구 같은놈들이 뭔가 사고칠 것 같음
    @Transactional
    public Map<String, Object> updateUserPoints(Long userId, Integer pointChange) {
        // 1. userId가 null인지 검증
        // - 클라이언트에서 잘못된 데이터가 넘어올 경우를 대비
        if (userId == null) {
            return Map.of(
                    "success", false,
                    "message", "사용자 ID가 유효하지 않습니다."
            );
        }

        // 2. 현재 사용자의 포인트를 DB에서 조회
        Integer currentPoints = adminUserMapper.getCurrentUserPoints(userId);

        // 2-1. 새로운 포인트 계산 (현재 포인트 + 변경할 포인트)
        Integer newPoints = currentPoints + pointChange;

        // 3. 사용자가 존재하지 않는 경우 처리
        // - getCurrentUserPoints가 null을 반환하면 해당 userId의 사용자가 없는 것
        if (currentPoints == null) {
            return Map.of(
                    "success", false,
                    "message", "사용자를 찾을 수 없습니다."
            );
        }

        // 5. 포인트 감소로 인해 마이너스가 되는 경우 처리
        // - 포인트는 0 미만이 될 수 없음
        if (newPoints < 0) {
            return Map.of(
                    "success", false,
                    "message", "포인트가 부족합니다. 현재 포인트: " + currentPoints
            );
        }

        // 6. DB에 포인트 업데이트 실행
        // - updateUserPoints는 업데이트된 행의 수를 반환
        int updatedRows = adminUserMapper.updateUserPoints(userId, pointChange);

        // 7. 업데이트 결과 처리
        // - updatedRows > 0: 업데이트 성공
        // - updatedRows = 0: 업데이트 실패 (해당 사용자가 없거나 다른 문제)
        if (updatedRows > 0) {
            // 성공시 현재 포인트, 변경된 포인트, 최종 포인트 정보 반환
            return Map.of(
                    "success", true,
                    "currentPoints", currentPoints,  // 이전 포인트
                    "points", pointChange,          // 변경된 포인트 값
                    "newPoints", newPoints          // 최종 포인트
            );
        } else {
            // 실패시 에러 메시지 반환
            return Map.of(
                    "success", false,
                    "message", "포인트 업데이트에 실패했습니다."
            );
        }
    }

    // 벤 확인 처리를 위해 크기 비교 실패 했으면 0 false 반환 예정
    @Transactional
    public boolean banUser(Long userId, Long banDays, Long reportId) {
        int banResult = adminUserMapper.updateUserBanStatus(userId, banDays);
        int reportResult = adminUserMapper.updateReportStatus(reportId);
        return banResult > 0 && reportResult > 0;
    }
}
