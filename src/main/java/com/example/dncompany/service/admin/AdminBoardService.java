package com.example.dncompany.service.admin;

import com.example.dncompany.dto.admin.board.AdminAnswerDTO;
import com.example.dncompany.dto.admin.board.BoardSearchDTO;
import com.example.dncompany.mapper.admin.AdminBoardMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class AdminBoardService {
    private final AdminBoardMapper adminBoardMapper;

//    public List<AdminReportBoardDTO> getReportBoardSearch() {
//        return adminBoardMapper.selectReportBoard() ;
//    }
//    public List<AdminQnABoardDTO> getQnABoard() {
//        return adminBoardMapper.selectQnABoard(); }



    // @TODO 카테고리별 게시물 조회 (쿼리 완성시 case 하나씩 추가 예정)
    // 옛날 코드 곧 제거 예정
    public List<?> getBoardByCategory(String category) {
        // 카테고리에 따라 적절한 매퍼 메소드 호출
        switch (category.toLowerCase()) {
            case "qna":
                return adminBoardMapper.selectQnABoard();
            case "신고":
                return adminBoardMapper.selectReportBoard();
            case "도와주세요":
                return adminBoardMapper.selectHelpBoard();
            case "이벤트":
                return adminBoardMapper.selectEventBoard();
            case "all":
                // 전체 목록 조회 로직 (구현 필요)
                return adminBoardMapper.selectAllBoard();
            default:
                return adminBoardMapper.selectReportBoard();
        }
    }

    // 검색 조건에 따른 게시물 조회
    public List<?> getBoardBySearchCondition(BoardSearchDTO searchDTO) {
        // 카테고리가 전체(all)인 경우 전체 게시판 조회
        if ("all".equalsIgnoreCase(searchDTO.getCategory())) {
            return adminBoardMapper.selectBoardByCondition(searchDTO);
        }

        // 특정 카테고리 게시판 조회
        return adminBoardMapper.selectBoardByCondition(searchDTO);
    }


//    ===============================================================================================================================================================



    public void addAnswer(AdminAnswerDTO adminAnswerDTO) {
        // 카테고리에 따라 다른 답변 테이블에 insert
        switch(adminAnswerDTO.getCategory()) {
            case "QNA":
                adminBoardMapper.insertQnaAnswer(adminAnswerDTO);
                adminBoardMapper.updateQnaStatus(adminAnswerDTO.getQnaId());
                break;
//            case "신고":
//                adminBoardMapper.어쩌고 저쩌고 () 식으로 추가
//                break;
            // 다른 카테고리 추가 가능
        }
    }

    public List<AdminAnswerDTO> getAnswersByPostId(Long postId, String category) {
        // 카테고리에 따라 다른 답변 테이블에서 select
        switch(category) {
            case "QNA":
                return adminBoardMapper.selectQnaAnswersByQnaId(postId);
//            case "신고":
//                return adminBoardMapper.selectReportAnswers(postId);
            // 다른 카테고리 추가 가능
            default:
                return Collections.emptyList();
        }
    }

    public Map<String,Object> selectQnaDetail(Long qnaId) {
        try {
            Map<String, Object> qnaDetail = adminBoardMapper.selectQnaDetail(qnaId);
            if (qnaDetail == null) {
                // 데이터가 없을 경우 빈 맵 반환
                return Collections.emptyMap();
            }
            return qnaDetail;
        } catch (Exception e) {
            // 에러 발생 시 로그 기록 및 빈 맵 반환
            log.error("QnA 상세 조회 중 오류 발생: {}", e.getMessage());
            return Collections.emptyMap();
        }
    }




}
