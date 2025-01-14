package com.example.dncompany.service.user.message;

import com.example.dncompany.dto.user.message.MessageSendDTO;
import com.example.dncompany.mapper.user.message.send.SendModalMapper;
import com.example.dncompany.service.user.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class MessageSendService {
    private final SendModalMapper sendModalMapper;

    public void addMessageSend(MessageSendDTO messageSendDTO) {

        String loginIdTo = messageSendDTO.getLoginIdTo();
        // loginIdTo로 pk조회
        messageSendDTO.setUserTo(sendModalMapper.selectloginIdUserTo(loginIdTo));
        log.info(messageSendDTO.toString());

        sendModalMapper.insertSendModal(messageSendDTO);
    }
}

