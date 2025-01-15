package com.example.dncompany.controller.user;

import com.example.dncompany.dto.user.UserJoinDTO;
import com.example.dncompany.dto.user.UserLoginDTO;
import com.example.dncompany.dto.user.UserSessionDTO;
import com.example.dncompany.exception.user.LoginFailedException;
import com.example.dncompany.exception.user.UserDuplicateException;
import com.example.dncompany.service.user.AuthService;
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
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final AuthService authService;

    @GetMapping("/user/signup")
    public String signup() {
        return "user/signup";
    }

    @PostMapping("/user/signup")
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

    @GetMapping("/user/login")
    public String login(@RequestParam(defaultValue = "false") boolean hasError,
                        Model model) {
        model.addAttribute("hasError", hasError);
        return "user/login";
    }

    @PostMapping("/user/login")
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
//    카카오 인증
    @GetMapping("/user/auth/kakao/login")
    public String kakaoLogin() {
        System.out.println("User.kakaoLogin");

        String location = authService.getKakaoLoginURI();
        return "redirect:" + location;

    }

    @GetMapping("/auth/kakao/callback")
    public String kakaoCallback(String code) {
        System.out.println("code = " + code);

        authService.getKakaoLoginInfo(code);
        return "redirect:/user/signup";
//      유저 테이블에 카카오 인증 칼럼을 추가하고
//      카카오 인증으로 회원가입하면 칼럼에 인증했다는 기록을 남기고
//      이후 회원가입 창으로 이동시켜 정보를 입력받고
//      이 정보를 DB에 담은 다음에
//      이후 카카오 로그인을 했을 때는 DB에 입력 되어 있는 정보를 받아서
//      로그인 처리를 완료하는 방법이 가능한가.
    }

    @GetMapping("/user/logout")
    public String logout(HttpSession session) {
        session.invalidate();

        return "redirect:/";
    }



}
