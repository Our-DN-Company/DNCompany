package com.example.dncompany.mapper.dn;

import com.example.dncompany.dto.dn.DnBoardDTO;
import com.example.dncompany.dto.dn.ProductDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@Transactional
class DnMapperTest {

    @Autowired
    private DnMapper dnMapper;

    DnBoardDTO dnBoardDTO;
    ProductDTO productDTO;


    @BeforeEach
    void setUp() {
        dnBoardDTO = new DnBoardDTO();
        productDTO = new ProductDTO();
        dnBoardDTO.setUsersId(6L);
        dnBoardDTO.setDnId(1L);
        dnBoardDTO.setDnTitle("테스트용 타이틀이다~~");
        dnBoardDTO.setDnContent("테스트용 내용 작성!!");
        dnBoardDTO.setDnPetCategory("강아지");
        productDTO.setProductCategory("간식");
        productDTO.setProductPrice(20000);
        productDTO.setProductId(1L);
    }

    @Test
    void insertDnBoard() {
        // given
        dnMapper.insertDnBoard(dnBoardDTO);
        dnMapper.insertProduct(productDTO);
        // when

        // then


    }

    @Test
    void insertProduct() {
    }
}