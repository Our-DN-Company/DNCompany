package com.example.dncompany.mapper.dn;

import com.example.dncompany.dto.dn.*;
import com.example.dncompany.dto.page.PageRequestDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;


@Mapper
public interface DnBoardMapper {
    // 게시글 추가
    void insertDnBoard (DnBoardWriteDTO dnBoardWriteDTO);

    // 특정 게시글 한 건 조회
    Optional<DnBoardDetailDTO> selectDnBoardById (Long dnId);

    // 전체 게시글 조회
    List<DnBoardListDTO> selectAllDnBoardList ();

    // sell 테이블 삽입용
    void insertSellBoard (DnSellBoardDTO dnSellBoardDTO);

    // 게시글 수정
    void updateDnBoard (DnBoardModifyDTO dnBoardModifyDTO);

    // 게시글 삭제
    void deleteDnBoard (Long dnId);

    // 게시글 삭제하면서 sell 테이블에 있는 값도 같이 삭제
    void deleteDnSell (Long dnId);

    // 게시글 전체 조회하면서 페이징 처리
    List<DnBoardListDTO> selectAllDnBoardListWithPage(@Param("page") PageRequestDTO pageRequestDTO);

    // 게시글 전체 페이지 수 조회
    int selectAllDnBoardListCondition();
}

