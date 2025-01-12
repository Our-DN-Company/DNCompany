package com.example.dncompany.controller.user;

import com.example.dncompany.dto.user.MessagePageDTO;
import com.example.dncompany.service.user.MessageService;
import com.example.dncompany.service.user.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class MessageController {

    private final MessageService messageService;
    @GetMapping("/message")
    public String message(Model model) {
        List<MessagePageDTO> messagePageDTOList = messageService.addMessageBoard();
        model.addAttribute("messagePageDTOList", messagePageDTOList);

        return "user/message";
    }
}
