package com.example.dncompany.controller.user.message;

import com.example.dncompany.dto.page.PageDTO;
import com.example.dncompany.dto.page.PageRequestDTO;
import com.example.dncompany.dto.user.UserLoginDTO;
import com.example.dncompany.dto.user.UserSessionDTO;
import com.example.dncompany.dto.user.message.MessagePageDTO;
import com.example.dncompany.dto.user.message.MessageSendDTO;
import com.example.dncompany.dto.user.mypage.MypageZipAnswerListDTO;
import com.example.dncompany.dto.user.mypage.MypageZipBoardListDTO;
import com.example.dncompany.exception.user.message.MessageSendException;
import com.example.dncompany.service.user.UserService;
import com.example.dncompany.service.user.message.MessageSendService;
import com.example.dncompany.service.user.message.MessageService;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class MessageController {

    private final MessageService messageService;
    private final MessageSendService messageSendService;

    @GetMapping("/message")
    public String message(@SessionAttribute(value = "usersId", required = false) Long usersId,
                          Long userTo,
                          Long userFrom,
                          PageRequestDTO pageRequestDTO,
                          Model model){
        if(usersId == null) {
            return "redirect:/user/login";
        }

        return "user/message";
    }

    @PostMapping("/message")
    public String message(MessageSendDTO messageSendDTO, HttpSession session) {
        log.debug("messageSendDTO: {}", messageSendDTO);

        // 세션에서 로그인한 사용자 ID를 가져오기
        Long userId = (Long) session.getAttribute("usersId");


        // messageSendDTO에 userFrom 설정
        messageSendDTO.setUserFrom(userId);

            messageSendService.addMessageSend(messageSendDTO);
            return "redirect:/user/message";

    }


}
