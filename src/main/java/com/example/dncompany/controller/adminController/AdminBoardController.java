package com.example.dncompany.controller.adminController;

import com.example.dncompany.dto.admin.board.AdminReportBoardDTO;
import com.example.dncompany.dto.admin.board.BoardSearchDTO;
import com.example.dncompany.service.admin.AdminBoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/admin/board")
@Controller
@RequiredArgsConstructor
public class AdminBoardController {
    private final AdminBoardService adminBoardService;


    @GetMapping("/list")
    public String list() {
        return "admin/admin_board/admin_board";
    }

    // 카테고리별 게시판 데이터 조회
//    @PostMapping("/list/reportBoard")
//    public String adminBoardList(@RequestParam String category, Model model) {
//        // 카테고리에 따른 게시물 데이터 조회
//        List<?> boards = adminBoardService.getBoardByCategory(category);
//        model.addAttribute("boardType", boards);
//        return "admin/admin_board/admin_board :: #postListBody";
//    }

    // 게시판 데이터 검색 및 조회
    // 위에 코드 기반으로 개조 테스트 후 문제 없으면 위에 코드 삭제 예정
    @PostMapping("/list/reportBoard")
    public String adminBoardList(@ModelAttribute BoardSearchDTO searchDTO, Model model) {
        List<?> boards = adminBoardService.getBoardBySearchCondition(searchDTO);
        model.addAttribute("boardType", boards);
        return "admin/admin_board/admin_board :: #postListBody";
    }
}