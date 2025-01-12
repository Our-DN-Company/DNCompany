package com.example.dncompany.service.user;

import com.example.dncompany.dto.user.MessagePageDTO;
import com.example.dncompany.mapper.user.MessageMapper;
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

    public List<MessagePageDTO> addMessageBoard() { return messageMapper.selectfromMessage(); }
}
