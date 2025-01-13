package com.example.dncompany.mapper.user.message.send;

import com.example.dncompany.dto.user.message.MessageSendDTO;
import com.example.dncompany.service.user.message.MessageSendService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
@Transactional
class SendModalMapperTest {

    @Autowired
    SendModalMapper sendModalMapper;

    MessageSendDTO messageSendDTO;
    @Autowired
    private MessageSendService messageSendService;

    @BeforeEach
    void setUp() {
        messageSendDTO = new MessageSendDTO();
        messageSendDTO.setUserFrom(43L);
        messageSendDTO.setUserTo(42L);
        messageSendDTO.setMsContent("42에게 보낸 메세지");
    }

    @Test
    void insertSendModal() {
        //given
        //when
        sendModalMapper.insertSendModal(messageSendDTO);
        //then
    }

    @Test
    void selectloginIdUserTo() {
        // given
        String loginId = "aaa1234";

        // when
        Long userId = sendModalMapper.selectloginIdUserTo(loginId);

        // then
        assertThat(userId).isNotNull();
        assertThat(userId).isEqualTo(42L);
    }
}