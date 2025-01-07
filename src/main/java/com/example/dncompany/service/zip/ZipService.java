package com.example.dncompany.service.zip;

import com.example.dncompany.dto.zip.ZipBoardListDTO;
import com.example.dncompany.dto.zip.ZipBoardWriteDTO;
import com.example.dncompany.mapper.zip.ZipMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ZipService {
    private final ZipMapper zipMapper;

    // 게시물 삽입
    public void addZipBoard(ZipBoardWriteDTO zipWriteBoard){
        zipMapper.insertZipBoard(zipWriteBoard);
    }

    // 게시물 전체 정보
    public List<ZipBoardListDTO> getAllZipBoards(){
        return zipMapper.selectAllZipBoards();
    }
}





























