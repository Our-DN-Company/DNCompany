package com.example.dncompany.controller.adminController;

import com.example.dncompany.dto.admin.user.board.AdminUserAllBoard;
import com.example.dncompany.service.admin.AdminUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/admin/user/board")
@RequiredArgsConstructor
public class AdminUserBoardController {

    private final AdminUserService adminUserService;

    @GetMapping("/list")
    public String list(Model model) {
        // 기본적으로 첫 화면에 모든 사용자 데이터를 표시
//        List<AdminUserAllBoard> userList = adminUserService.getAllUserData(new AdminUserAllBoard());
//        model.addAttribute("userList", userList);

        return "admin/admin_board/admin_user_board";
    }

    // 회원정보 검색 및 조회 (부분 업데이트)
    @PostMapping("/list/data")
    public String listData(AdminUserAllBoard searchCriteria, Model model) {
        // 검색조건에 맞는 회원 리스트 조회
        List<AdminUserAllBoard> userList = adminUserService.getAllUserData(searchCriteria);
        model.addAttribute("userList", userList);
        return "admin/admin_board/admin_user_board :: #memberListBody";  // 특정 부분만 갱신
    }
}
