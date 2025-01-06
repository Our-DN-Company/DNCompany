package com.example.dncompany.service.admin;

import com.example.dncompany.dto.admin.board.AdminQnABoardDTO;
import com.example.dncompany.dto.admin.board.AdminReportBoardDTO;
import com.example.dncompany.dto.admin.board.BoardSearchDTO;
import com.example.dncompany.mapper.admin.AdminBoardMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

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

}
