package com.example.dncompany.mapper;

import com.example.dncompany.dto.MainDnDTO;
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
class MainpageMapperTest {
    @Autowired
    MainpageMapper mainpageMapper;

    MainDnDTO mainDnDTO;


    @Test
    void selectMainpageDnboard4Post() {
        // given
        // when
        List<MainDnDTO> maindnDTOList = mainpageMapper.selectMainpageDnboard4Post();
        // then
        assertThat(maindnDTOList).isNotEmpty()
                .extracting("dnTitle")
                .contains("장난감 팝니다");
    }
}