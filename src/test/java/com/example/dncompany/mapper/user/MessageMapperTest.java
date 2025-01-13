package com.example.dncompany.mapper.user;

import com.example.dncompany.dto.user.message.MessagePageDTO;
import com.example.dncompany.mapper.user.message.MessageMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class MessageMapperTest {
    @Autowired
    MessageMapper messageMapper;

    MessagePageDTO messagePageDTO;

    @BeforeEach
    void setUp() {
        // messagePageDTO 객체를 초기화하거나 실제 데이터를 넣어줍니다.
        messagePageDTO = new MessagePageDTO();
        messagePageDTO.setUsersId(42L);
        messagePageDTO.setUserFrom(42L);  // 예시로 42를 설정
        messagePageDTO.setUserTo(42L);  // 예시로 42를 설정
    }

    @Test
    void selectfromMessage() {
        // given

        // when
        List<MessagePageDTO> messagePageDTOList = messageMapper.selectFromMessage(messagePageDTO.getUsersId());
        // then
        assertThat(messagePageDTOList).isNotEmpty()
                .extracting("userFrom")
                .contains (42L);
    }

    @Test
    void selectToMessage() {
        // given

        // when
        List<MessagePageDTO> messagePageDTOList = messageMapper.selectToMessage(messagePageDTO.getUsersId());
        // then
        assertThat(messagePageDTOList).isNotEmpty()
                .extracting("userTo")
                .contains (42L);
    }

}