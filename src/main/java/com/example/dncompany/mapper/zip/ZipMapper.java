package com.example.dncompany.mapper.zip;

import com.example.dncompany.dto.zip.ZipBoardDetailDTO;
import com.example.dncompany.dto.zip.ZipBoardWriteDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.Optional;

@Mapper
public interface ZipMapper {
    void insertZipBoard(ZipBoardWriteDTO zipWriteBoard);

    Optional<ZipBoardDetailDTO> selectById (Long freeBoardId);
}
