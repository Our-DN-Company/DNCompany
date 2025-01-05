package com.example.dncompany.mapper.admin;


import com.example.dncompany.dto.admin.board.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AdminBoardMapper {

    List<AdminReportBoardDTO> selectReportBoard();

    List<AdminQnABoardDTO> selectQnABoard();

    List<AdminHelpBoardDTO> selectHelpBoard();

    List<AdminEventBoardDTO> selectEventBoard();



    // @TODO 전체 게시판 조회 (미완성임)
    // 곧 삭제 예정 테스트 후 결과보고 제거 예정임
    List<AdminAllBoardDTO> selectAllBoard();

    // 검색 조건을 포함한 게시판 조회
    List<AdminAllBoardDTO> selectBoardByCondition(BoardSearchDTO searchDTO);

}
