package com.example.dncompany.controller.user.message.list;

import com.example.dncompany.dto.page.PageDTO;
import com.example.dncompany.dto.page.PageRequestDTO;
import com.example.dncompany.dto.user.message.MessagePageDTO;
import com.example.dncompany.service.user.message.MessageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class MessagelistApi {
    private final MessageService messageService;

    @GetMapping("/v1/message/listTos")
    public PageDTO<MessagePageDTO> getmessageToPage(PageRequestDTO pageRequestDTO,
                                                    @SessionAttribute(value = "usersId") Long userTo) {
        PageDTO<MessagePageDTO> messagePageDTOTo = messageService.messageWithToPage(pageRequestDTO, userTo);
        return messagePageDTOTo;

    }
    @GetMapping("/v1/message/listFroms")
    public PageDTO<MessagePageDTO> getmessageFromPage(PageRequestDTO pageRequestDTO,
                                                      @SessionAttribute(value = "usersId") Long userFrom) {
        PageDTO<MessagePageDTO> messagePageDTOFrom = messageService.messageWithFromPage(pageRequestDTO, userFrom);
        return messagePageDTOFrom;

    }

    @DeleteMapping("/v1/message/{messageId}")
    public void deleteFreeComment(@PathVariable("messageId") Long messageId) {
        messageService.removeBymessageId(messageId);
    }
}

















