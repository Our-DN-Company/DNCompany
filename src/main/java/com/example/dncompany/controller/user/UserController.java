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

import java.util.Date;

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

            log.info("UserStatus: {}, BanEndDate: {}, CurrentTime: {}",
                    loginInfo.getUserStatus(), loginInfo.getBanEndDate(), new Date());

            /**(제현)
             * 서비스로 옴겨 같음 정지일때도 모두 아이디 비번 오류로 처리함
             * 애초에 쿼리가 잘 못 됬음 쿼리에서 조건을 시스데이트보다 정지 만료일이
             * 작은 데이터만 뽑으라니까 정지인 사람은 애초에 뽑지 못하고 null이 반환됨
             * 그래서 로그인은 불가능하지만 정확한 비교가 불가능했음
             * 쿼리를 수정 후 검사 로직을 서비스로 옴김
             */
            // 정지 상태 확인 로직
//            if ("SUSPENDED".equals(loginInfo.getUserStatus())) {
//                log.warn("계정 정지 상태 - 정지 해제 시간: {}", loginInfo.getBanEndDate());
//                redirectAttributes.addFlashAttribute("message",
//                        "계정이 정지되었습니다. 정지 해제 날짜: " + loginInfo.getBanEndDate());
//                // 정지 해제 날짜 확인
//                if (loginInfo.getBanEndDate() != null) {
//                    redirectAttributes.addFlashAttribute("message",
//                            "계정이 정지되었습니다. 정지 해제 날짜: " + loginInfo.getBanEndDate());
//                    return "redirect:/user/login";
//                }
//            }


            session.setAttribute("usersId", loginInfo.getUsersId());
            session.setAttribute("loginId", loginInfo.getLoginId());
            session.setAttribute("role", loginInfo.getRole());

            log.info("userLoginDTO: {}", userLoginDTO);
            log.debug("userLoginDTO: {}", userLoginDTO);

            return "redirect:/";
        } catch (LoginFailedException e) {
            log.error(e.getMessage());
            log.error("로그인 실패: {}", e.getMessage());

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
