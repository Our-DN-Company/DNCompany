package com.example.dncompany.service.qna;

import com.example.dncompany.dto.qna.QnADTO;
import com.example.dncompany.dto.qna.QnAWriteDTO;
import com.example.dncompany.mapper.qna.QnaMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class QnaService {
    private final QnaMapper qnaMapper;

    // 게시글 삽입
    public void addQnaBoard(QnAWriteDTO qnaWriteDTO) {
        qnaMapper.insertQnABoard(qnaWriteDTO);
    }

    // 게시글 전체 정보
    public List<QnADTO> getAllQnaBoards() {
        return qnaMapper.selectAllQnABoards();
    }
}
