package com.example.dncompany.mapper.dn;

import com.example.dncompany.dto.dn.file.DnFileDTO;
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
    DnFileDTO dnfileDTO;


    @Test
    void insertFile_And_SelectById() {
        // given
        dnFileMapper.insertFile(dnfileDTO);
        // when

        // then
    }
}