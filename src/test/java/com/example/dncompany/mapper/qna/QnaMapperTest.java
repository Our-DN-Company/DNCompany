package com.example.dncompany.mapper.qna;

import com.example.dncompany.dto.qna.QnADTO;
import com.example.dncompany.dto.qna.QnAWriteDTO;
import com.example.dncompany.mapper.zip.ZipMapper;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@SpringBootTest
@Transactional
class QnaMapperTest {
    @Autowired
    QnaMapper qnaMapper;

    QnAWriteDTO qnaWriteDTO;

    @BeforeEach
    void setUp() {
        qnaWriteDTO = new QnAWriteDTO();
        qnaWriteDTO.setQnaTitle("장기 돌봄 시 주의사항");
        qnaWriteDTO.setUserId(23L);
    }

    @Test
    void selectAllQnABoards() {
        // given
        qnaMapper.insertQnABoard(qnaWriteDTO);
        // when
        List<QnADTO> qnaList = qnaMapper.selectAllQnABoards();
        // then

        Assertions.assertThat(qnaList)
                .isNotEmpty()
                .extracting("qnaTitle")
                .contains("장기 돌봄 시 주의사항");
    }
}