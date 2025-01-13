package com.example.dncompany.mapper.dn;

import com.example.dncompany.dto.dn.*;
import org.apache.ibatis.annotations.Mapper;

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
}

