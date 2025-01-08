package com.example.dncompany.mapper.zip;

import com.example.dncompany.dto.zip.ZipBoardListDTO;
import com.example.dncompany.dto.zip.ZipBoardWriteDTO;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
@Transactional
class ZipMapperTest {
    @Autowired
    ZipMapper zipMapper;

    ZipBoardWriteDTO zipBoardWriteDTO;

    @BeforeEach
    void setUp() {
        zipBoardWriteDTO = new ZipBoardWriteDTO();
        zipBoardWriteDTO.setZipTitle("test title");
        zipBoardWriteDTO.setUserId(21L);
    }


    @Test
    void selectAllZipBoards() {
        // given
        zipMapper.insertZipBoard(zipBoardWriteDTO);
        // when
        List<ZipBoardListDTO> zipBoardList = zipMapper.selectAllZipBoards();
        // then
        assertThat(zipBoardList)
                .isNotEmpty()
                .extracting("zipTitle")
                .contains("test title");
    }

}