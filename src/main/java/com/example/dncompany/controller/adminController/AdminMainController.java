package com.example.dncompany.controller.adminController;


import com.example.dncompany.dto.admin.main.AdminBoardCountDTO;
import com.example.dncompany.dto.admin.main.AdminCardCountDTO;
import com.example.dncompany.dto.admin.main.AdminCardLastWeekDTO;
import com.example.dncompany.dto.admin.main.AdminUserCountDTO;
import com.example.dncompany.mapper.admin.AdminMainMapper;
import com.example.dncompany.service.admin.AdminMainService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Optional;

@Slf4j
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

        // 카드 섹션 카운트
        Optional<AdminCardCountDTO> cardCounts = adminMainService.getDailyCardCounts();
        if (cardCounts.isPresent()) {
            model.addAttribute("cardCounts", cardCounts.get());
        }
        else {
            log.info(cardCounts.toString());
        }
        // 카드 섹션 저번 주 카운트
        Optional<AdminCardLastWeekDTO> cardLastWeekCounts = adminMainService.getDailyCardLastWeek();
        if (cardLastWeekCounts.isPresent()) {
            model.addAttribute("cardLastWeekCounts", cardLastWeekCounts.get());
        }
        else {
            log.info(cardLastWeekCounts.toString());
            log.info("Received Update Points: {}", cardLastWeekCounts);
        }



        log.info("cardCountsDTO: {}" , cardCounts);



         return "admin/admin_main/admin_main";
    }

    @GetMapping("/api/v1/board-counts")
    @ResponseBody
    public List<AdminBoardCountDTO> getBoardCounts() {
        return adminMainService.getDailyBoardCounts();
    }



}
