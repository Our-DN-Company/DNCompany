package com.example.dncompany.mapper.user;

import com.example.dncompany.dto.user.MessagePageDTO;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class MessageMapperTest {
    @Autowired
    MessageMapper messageMapper;

    @BeforeEach
    void setUp() {
    }

    @Test
    void selectfromMessage() {
        // given
        // when
        List<MessagePageDTO> messagePageDTOList = messageMapper.selectfromMessage();
        // then
        assertThat(messagePageDTOList).isNotEmpty()
                .extracting("loginId")
                .contains("aaa1234");
    }
}