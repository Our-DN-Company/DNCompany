package com.example.dncompany.service.qna;

import com.example.dncompany.dto.qna.QnADetailDTO;
import com.example.dncompany.mapper.qna.QnaMapper;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class QnaServiceTest {
    @Mock
    QnaMapper qnaMapper;
    @InjectMocks
    QnaService qnaService;

    @Test
    void getQnAById() {
        // given
        when(qnaMapper.QnASelectById(any())).thenReturn(Optional.of(new QnADetailDTO()));
        // when
        QnADetailDTO foundQnA = qnaService.getQnAById(2L);
        // then
        verify(qnaMapper).QnASelectById(any());
        assertThat(foundQnA).isNotNull();
    }

}