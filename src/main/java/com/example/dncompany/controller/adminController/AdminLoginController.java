package com.example.dncompany.controller.adminController;


import com.example.dncompany.dto.admin.login.AdminLoginDTO;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminLoginController {


    @PostMapping("/login")
    public String AdminLogin(AdminLoginDTO adminLoginDTO,
                             HttpSession session,
                             RedirectAttributes redirectAttributes) {

        return "login";
    }
}
