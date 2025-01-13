package com.example.dncompany.service.user.message;

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
}
