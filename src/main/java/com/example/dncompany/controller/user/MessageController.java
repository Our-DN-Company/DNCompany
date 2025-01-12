package com.example.dncompany.controller.user;

import com.example.dncompany.dto.user.message.MessagePageDTO;
import com.example.dncompany.service.user.MessageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class MessageController {

    private final MessageService messageService;
    @GetMapping("/message")
    public String message(@SessionAttribute(value = "usersId", required = false) Long usersId,
                          Model model) {
        if(usersId == null) {
            return "redirect:/user/login";
        }
        List<MessagePageDTO> messageFromDTOList = messageService.addMessageBoardFrom(usersId);
        log.debug("messageFromDTOList: {}", messageFromDTOList);
        List<MessagePageDTO> messageToDTOList = messageService.addMessageBoardTo(usersId);
        log.debug("messageToDTOList: {}", messageToDTOList);

        model.addAttribute("messageFromDTOList", messageFromDTOList);
        model.addAttribute("messageToDTOList", messageToDTOList);

        return "user/message";
    }
}
