package com.example.dncompany.service.user.message;

import com.example.dncompany.dto.page.PageDTO;
import com.example.dncompany.dto.page.PageRequestDTO;
import com.example.dncompany.dto.user.message.MessagePageDTO;
import com.example.dncompany.mapper.user.message.MessageMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class MessageService {
    private final MessageMapper messageMapper;

    public List<MessagePageDTO> addMessageBoardFrom(Long usesId) {
        return messageMapper.selectFromMessage(usesId);
    }
    public List<MessagePageDTO> addMessageBoardTo(Long usesId) {
        return messageMapper.selectToMessage(usesId);
    }

    public PageDTO<MessagePageDTO> getMessageWithPage(PageRequestDTO pageRequestDTO, Long userId, boolean isFrom) {
        List<MessagePageDTO> messages;
        int total;

        if (isFrom) {
            // 보내는 사람 기준 페이징 메시지 조회
            messages = messageMapper.selectFromMessagePage(pageRequestDTO);
            total = messageMapper.countByPage(userId, null);
        } else {
            // 받는 사람 기준 페이징 메시지 조회
            messages = messageMapper.selectToMessagePage(pageRequestDTO);
            total = messageMapper.countByPage(null, userId);
        }

        return new PageDTO<>(
                pageRequestDTO.getPage(),
                pageRequestDTO.getSize(),
                total,
                messages
        );

    }
}
