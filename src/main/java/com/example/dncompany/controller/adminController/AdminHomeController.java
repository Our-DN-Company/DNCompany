package com.example.dncompany.controller.adminController;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminHomeController {

    @GetMapping("/main")
    public String adminmain() {
        return "admin/admin_main/admin_main";
    }

    @GetMapping("/login")
    public String adminlogin() {
        return "admin/admin_login/login";
    }

}
