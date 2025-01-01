package com.example.dncompany.controller.adminController;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/event")
public class AdminEventWriteContoller {

    @GetMapping("/write")
    public String adminEventWrite() {
        return "admin/admin_write/admin_write";
    }
}
