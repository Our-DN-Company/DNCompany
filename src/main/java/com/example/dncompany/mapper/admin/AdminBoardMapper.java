package com.example.dncompany.mapper.admin;


import com.example.dncompany.dto.admin.board.AdminQnABoardDTO;
import com.example.dncompany.dto.admin.board.AdminReportBoardDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AdminBoardMapper {

    List<AdminReportBoardDTO> selectReportBoard();

    List<AdminQnABoardDTO> selectQnABoard();


    // @TODO 전체 게시판 조회 (미완성임)
    List<?> selectAllBoard();

}
