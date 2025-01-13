package com.example.dncompany.service.user.message;

import com.example.dncompany.mapper.user.message.send.SendModalMapper;
import org.apache.ibatis.annotations.Mapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class MessageSendServiceTest {
    @Mapper
    SendModalMapper sendModalMapper;
    @InjectMocks
    MessageSendService messageSendService;

    @Test
    void addMessageSend() {
    }
}