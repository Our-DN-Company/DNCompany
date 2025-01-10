package com.example.dncompany.controller.adminController;

import com.example.dncompany.dto.admin.user.AdmInUserReportDTO;
import com.example.dncompany.dto.admin.user.board.AdminUserAllBoard;
import com.example.dncompany.service.admin.AdminUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Slf4j
@Controller
@RequestMapping("/admin/user/board")
@RequiredArgsConstructor
public class AdminUserBoardController {

    private final AdminUserService adminUserService;

    @GetMapping("/list")
    public String list(Model model) {
        return "admin/admin_board/admin_user_board";
    }

    // 회원정보 검색 및 조회 (부분 업데이트)
    @PostMapping("/list/data")
    public String listData(@ModelAttribute AdminUserAllBoard searchCriteria, Model model) {


        log.info("Received Search Criteria: {}", searchCriteria);
        log.info("Search Sign Start Date: {}", searchCriteria.getSearchSignStartDate());
        log.info("Search Sign End Date: {}", searchCriteria.getSearchSignEndDate());


        List<AdminUserAllBoard> userList = adminUserService.getAllUserData(searchCriteria);
        model.addAttribute("userList", userList);
        return "admin/admin_board/admin_user_board :: #memberListBody";
    }


    // 신고 내역 조회
    @GetMapping("/reportDetails/{userId}")
    @ResponseBody  // JSON 응답을 위해 추가
    public ResponseEntity<List<AdmInUserReportDTO>> getReportDetails(@PathVariable Long userId) {
        List<AdmInUserReportDTO> reports = adminUserService.getReportsByUserId(userId);
        return ResponseEntity.ok(reports);
    }

    // 신고 처리
    @PostMapping("/processReport/{reportId}")
    @ResponseBody  // JSON 응답을 위해 추가
    public ResponseEntity<Map<String, Object>> processReport(
            @PathVariable Long reportId,
            @RequestBody Map<String, Integer> request) {

        String status = "처리완료";  // 실제 DB에 맞는 상태값으로 수정
        boolean success = adminUserService.updateReportStatus(reportId, status);

        Map<String, Object> response = new HashMap<>();
        response.put("success", success);

        return ResponseEntity.ok(response);
    }

    // 활동 정지 처리
    @PostMapping("/banUser/{userId}")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> banUser(
            @PathVariable Long userId,
            @RequestBody Map<String, Integer> request) {

        Integer banDays = request.get("banDays");
        boolean success = adminUserService.banUser(userId, banDays);

        Map<String, Object> response = new HashMap<>();
        response.put("success", success);

        return ResponseEntity.ok(response);
    }

    // 포인트 수정
    @PostMapping("/updatePoints/{userId}")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> updatePoints(
            @PathVariable Long userId,
            @RequestBody Map<String, Integer> request) {
        log.info("Received Update Points: {}", request);


        Integer points = request.get("points");
        Map<String, Object> result = adminUserService.updateUserPoints(userId, points);

        return ResponseEntity.ok(result);
    }
}