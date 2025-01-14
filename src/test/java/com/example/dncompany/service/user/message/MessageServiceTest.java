package com.example.dncompany.service.user.message;

import com.example.dncompany.dto.page.PageDTO;
import com.example.dncompany.dto.page.PageRequestDTO;
import com.example.dncompany.dto.user.message.MessagePageDTO;
import com.example.dncompany.service.user.message.MessageService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

@SpringBootTest
@Transactional
class MessageServiceTest {

    @Autowired
    private MessageService messageService;

    @Test
    void testMessageWithToPage() {
        // Given
        Long userTo = 42L; // 실제 DB에 존재하는 userTo를 사용해야 합니다.
        PageRequestDTO pageRequestDTO = new PageRequestDTO();

        // When
        PageDTO<MessagePageDTO> result = messageService.messageWithToPage(pageRequestDTO, userTo);

        // Then
        assertThat(result).isNotNull();

        // Page 정보 검증
        assertThat(result.getPage()).isEqualTo(1);
        assertThat(result.getTotal()).isGreaterThan(0); // DB에 데이터가 있어야 성공
        assertThat(result.getTotalPages()).isGreaterThanOrEqualTo(1);
        assertThat(result.getStartPage()).isEqualTo(1);
        assertThat(result.getEndPage()).isLessThanOrEqualTo(result.getTotalPages());

        // 데이터 검증
        assertThat(result.getList()).isNotNull();
        assertThat(result.getList()).hasSizeLessThanOrEqualTo(5); // 한 페이지의 크기 제한
    }

    @Test
    void testMessageWithFromPage() {
        // Given
        Long userFrom = 42L; // 실제 DB에 존재하는 userFrom를 사용해야 합니다.
        PageRequestDTO pageRequestDTO = new PageRequestDTO();

        // When
        PageDTO<MessagePageDTO> result = messageService.messageWithFromPage(pageRequestDTO, userFrom);

        // Then
        assertThat(result).isNotNull();

        // Page 정보 검증
        assertThat(result.getPage()).isEqualTo(1);
        assertThat(result.getTotal()).isGreaterThan(0); // DB에 데이터가 있어야 성공
        assertThat(result.getTotalPages()).isGreaterThanOrEqualTo(1);
        assertThat(result.getStartPage()).isEqualTo(1);
        assertThat(result.getEndPage()).isLessThanOrEqualTo(result.getTotalPages());

        // 데이터 검증
        assertThat(result.getList()).isNotNull();
        assertThat(result.getList()).hasSizeLessThanOrEqualTo(5); // 한 페이지의 크기 제한
    }
}