package com.example.dncompany.controller.adminController;


import com.example.dncompany.dto.admin.login.AdminLoginDTO;
import com.example.dncompany.dto.admin.login.AdminSessionDTO;
import com.example.dncompany.exception.user.LoginFailedException;
import com.example.dncompany.mapper.admin.AdminLoginMapper;
import com.example.dncompany.service.admin.AdminLoginService;
import com.example.dncompany.service.user.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminLoginController {


    private final AdminLoginService adminLoginService;


    @GetMapping("/login")
    public String AdminLogin(@RequestParam(defaultValue = "false") boolean hasError,
                             Model model) {
        model.addAttribute("hasError", hasError);


        return "admin/admin_login/login";

    }



    @PostMapping("/login")
    public String AdminLogin(AdminLoginDTO adminLoginDTO,
                             HttpSession session,
                             RedirectAttributes redirectAttributes) {
        log.info("adminLoginDTO: " + adminLoginDTO);

        try {
            AdminSessionDTO adminLoginInfo = adminLoginService.getAdminLoginInfo(adminLoginDTO);

            // role 체크
            if ("ROLE_ADMIN".equals(adminLoginInfo.getRole())) {
                // admin인 경우 세션 설정하고 메인으로
                session.setAttribute("userId", adminLoginInfo.getUsersId());
                session.setAttribute("loginId", adminLoginInfo.getLoginId());
                session.setAttribute("role", adminLoginInfo.getRole());
                return "redirect:/admin/main";
            } else {
                // admin이 아닌 경우 로그인 페이지로
                redirectAttributes.addFlashAttribute("hasError", true);
                redirectAttributes.addFlashAttribute("message", "관리자만 접근 가능합니다.");
                return "redirect:/admin/login";
            }


        } catch (LoginFailedException e) {
            log.error(e.getMessage());
        log.info("adminLoginDTO: " + adminLoginDTO);

            redirectAttributes.addAttribute("hasError",true);

            redirectAttributes.addAttribute("message",e.getMessage());

//            return "redirect:/admin/admin_login/login";
            return "redirect:/admin/login";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/admin/login";
    }
}
