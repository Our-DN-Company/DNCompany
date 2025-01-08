package com.example.dncompany.mapper.zip;

import com.example.dncompany.dto.zip.ZipBoardDetailDTO;
import com.example.dncompany.dto.zip.ZipBoardListDTO;
import com.example.dncompany.dto.zip.ZipBoardWriteDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface ZipMapper {
    // 게시물 삽입
    void insertZipBoard(ZipBoardWriteDTO zipWriteBoard);

    // 게시물 전체 정보
    List<ZipBoardListDTO> selectAllZipBoards();

    // 게시물 조회수
    void updateViewCount(Long zipId);
}
