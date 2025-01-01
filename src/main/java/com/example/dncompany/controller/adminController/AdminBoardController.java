package com.example.dncompany.controller.adminController;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/admin/board")
@Controller
@RequiredArgsConstructor
public class AdminBoardController {

    @GetMapping("/list")
    public String list() {
        return "admin/admin_board/admin_board";
    }
}
