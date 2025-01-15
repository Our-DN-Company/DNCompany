package com.example.dncompany.controller.user.message.list;

import com.example.dncompany.dto.page.PageDTO;
import com.example.dncompany.dto.page.PageRequestDTO;
import com.example.dncompany.dto.user.message.MessagePageDTO;
import com.example.dncompany.mapper.user.message.MessageMapper;
import com.example.dncompany.service.user.message.MessageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class MessagelistApi {
    private final MessageMapper messageMapper;

    // 보낸 메시지 목록을 페이지네이션하여 반환
    @PatchMapping("/v1/messagelist-To/{usersId}")
    public PageDTO<MessagePageDTO> messageWithFromPage(@PathVariable("usersId") Long usersId,
                                                       @RequestBody PageRequestDTO pageRequestDTO,
                                                       Long userTo) {
        int totalMessages = messageMapper.countByTotalFrom(usersId);

        List<MessagePageDTO> messageList = messageMapper.selectFromMessagePage(pageRequestDTO, userTo);
        log.debug("messageWithFromPage, totalMessages={}", totalMessages);

        return new PageDTO<>(pageRequestDTO.getPage(), pageRequestDTO.getSize(), totalMessages, messageList);
    }

    // 받은 메시지 목록을 페이지네이션하여 반환
    @PatchMapping("/v1/messagelist-From/{usersId}")
    public PageDTO<MessagePageDTO> messageWithToPage(@PathVariable("usersId") Long usersId,
                                                     @RequestBody PageRequestDTO pageRequestDTO,
                                                     Long userFrom) {
        int totalMessages = messageMapper.countByTotalTo(usersId);
        List<MessagePageDTO> messageList = messageMapper.selectToMessagePage(pageRequestDTO, userFrom);
        log.debug("messageWithToPage, totalMessages={}", totalMessages);

        return new PageDTO<>(pageRequestDTO.getPage(), pageRequestDTO.getSize(), totalMessages, messageList);
    }
}

