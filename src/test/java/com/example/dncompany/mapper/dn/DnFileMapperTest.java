package com.example.dncompany.mapper.dn;

import com.example.dncompany.dto.dn.DnBoardWriteDTO;
import com.example.dncompany.dto.dn.file.DnFileDTO;
import com.example.dncompany.dto.user.UserJoinDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class DnFileMapperTest {
    @Autowired
    DnFileMapper dnFileMapper;
    @Autowired
    DnBoardMapper dnBoardMapper;
    @Autowired
    DnProductMapper dnProductMapper;

    DnFileDTO dnfileDTO;

    @BeforeEach
    void setUp() {

        dnfileDTO = new DnFileDTO();

        DnBoardWriteDTO dnBoardWriteDTO = new DnBoardWriteDTO();

        dnBoardMapper.insertDnBoard(dnBoardWriteDTO);

    }

    // file 테스트 진행중
    // insert file을 할 때

    @Test
    void insertFile_And_SelectById() {
        // given
        dnFileMapper.insertFile(dnfileDTO);
        // when
//        dnFileMapper.selectByBoardId()

        // then
    }
}