package com.example.dncompany.controller.user;

import com.example.dncompany.dto.user.UserJoinDTO;
import com.example.dncompany.dto.user.UserLoginDTO;
import com.example.dncompany.dto.user.UserSessionDTO;
import com.example.dncompany.exception.user.LoginFailedException;
import com.example.dncompany.exception.user.UserDuplicateException;
import com.example.dncompany.service.user.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Slf4j
@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/signup")
    public String signup() {
        return "user/signup";
    }

    @PostMapping("/signup")
    public String join(UserJoinDTO userJoinDTO) {
        log.debug("userJoinDTO: {}", userJoinDTO);
        log.info("userJoinDTO: {}", userJoinDTO);

        try {
            userService.addUser(userJoinDTO);
            return "redirect:/user/login";
        } catch (UserDuplicateException e) {
            log.error(e.getMessage());
            return "redirect:/user/signup";
        }
    }

    @GetMapping("/login")
    public String login(@RequestParam(defaultValue = "false") boolean hasError,
                        Model model) {
        model.addAttribute("hasError", hasError);
        return "user/login";
    }

    @PostMapping("/login")
    public String login(UserLoginDTO userLoginDTO,
                        HttpSession session,
                        RedirectAttributes redirectAttributes) {
        try {
            UserSessionDTO loginInfo = userService.getLoginInfo(userLoginDTO);
            session.setAttribute("usersId", loginInfo.getUsersId());
            session.setAttribute("loginId", loginInfo.getLoginId());
            session.setAttribute("role", loginInfo.getRole());

            log.info("userLoginDTO: {}", userLoginDTO);
            log.debug("userLoginDTO: {}", userLoginDTO);

            return "redirect:/";
        } catch (LoginFailedException e) {
            log.error(e.getMessage());

            redirectAttributes.addAttribute("hasError", true);

            redirectAttributes.addFlashAttribute("message", e.getMessage());

            return "redirect:/user/login";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();

        return "redirect:/";
    }

}
