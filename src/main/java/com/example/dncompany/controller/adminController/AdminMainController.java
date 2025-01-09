package com.example.dncompany.controller.adminController;


import com.example.dncompany.dto.admin.main.AdminBoardCountDTO;
import com.example.dncompany.dto.admin.main.AdminUserCountDTO;
import com.example.dncompany.mapper.admin.AdminMainMapper;
import com.example.dncompany.service.admin.AdminMainService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminMainController {

    private final AdminMainMapper adminMainMapper;
    private final AdminMainService adminMainService;

    @GetMapping("/main")
    public String adminmain(Model model) {

        // 보드 카운트
        List<AdminBoardCountDTO> boardCounts = adminMainService.getDailyBoardCounts();
        model.addAttribute("boardCounts", boardCounts);
        // 유저 카운트
        List<AdminUserCountDTO> userCounts = adminMainService.getDailyUserCounts();
        model.addAttribute("userCounts", userCounts);



         return "admin/admin_main/admin_main";
    }

    @GetMapping("/api/v1/board-counts")
    @ResponseBody
    public List<AdminBoardCountDTO> getBoardCounts() {
        return adminMainService.getDailyBoardCounts();
    }

    // 비슷한 코드끼리 묶기 위해 자리 옴김
    @GetMapping("/login")
    public String adminlogin() {
        return "admin/admin_login/login";
    }


}
