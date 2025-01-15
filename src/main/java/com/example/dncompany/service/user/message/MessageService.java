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

    public PageDTO<MessagePageDTO> messageWithToPage(PageRequestDTO pageRequestDTO, Long userTo) {


        List<MessagePageDTO> messageListTo = messageMapper.selectToMessagePage(pageRequestDTO, userTo);
        int total = messageMapper.countByTotalTo(userTo);

        return new PageDTO<>(pageRequestDTO.getPage(),
                pageRequestDTO.getSize(),
                total,
                messageListTo);
    }
    public PageDTO<MessagePageDTO> messageWithFromPage(PageRequestDTO pageRequestDTO,Long userFrom) {
        List<MessagePageDTO> messageListFrom = messageMapper.selectFromMessagePage(pageRequestDTO, userFrom);
        int total = messageMapper.countByTotalTo(userFrom);

        return new PageDTO<>(pageRequestDTO.getPage(),
                pageRequestDTO.getSize(),
                total,
                messageListFrom);
    }
}
