package com.example.dncompany.mapper.zip;

import com.example.dncompany.dto.user.UserJoinDTO;
import com.example.dncompany.dto.zip.ZipBoardDetailDTO;
import com.example.dncompany.dto.zip.ZipBoardWriteDTO;
import com.example.dncompany.mapper.user.UserMapper;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class ZipMapperTest {
    @Autowired
    ZipMapper zipMapper;
    @Autowired
    UserMapper userMapper;

    ZipBoardWriteDTO zipBoardWriteDTO;

    UserJoinDTO userJoinDTO;

    @BeforeEach
    void setUp() {

    }

    @Test
    void insertZipBoard() {
        // given
        zipMapper.insertZipBoard(zipBoardWriteDTO);
        // when
        Optional<ZipBoardDetailDTO> foundBoard = zipMapper.selectById(zipBoardWriteDTO.getZipId());
        // then
        assertThat(foundBoard)
                .isNotNull();
    }
}