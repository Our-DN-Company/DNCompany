package com.example.dncompany.mapper.dn;

import com.example.dncompany.dto.dn.DnBoardWriteDTO;
import com.example.dncompany.dto.dn.ProductDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;


@SpringBootTest
@Transactional
class DnMapperTest {

    @Autowired
    private DnBoardMapper dnBoardMapper;

    DnBoardWriteDTO dnBoardWriteDTO;
    ProductDTO productDTO;


    @BeforeEach
    void setUp() {
        dnBoardWriteDTO = new DnBoardWriteDTO();
        productDTO = new ProductDTO();
        dnBoardWriteDTO.setUsersId(6L);
        dnBoardWriteDTO.setDnId(1L);
        dnBoardWriteDTO.setDnTitle("테스트용 타이틀이다~~");
        dnBoardWriteDTO.setDnContent("테스트용 내용 작성!!");
        dnBoardWriteDTO.setDnPetCategory("강아지");
        productDTO.setProductCategory("간식");
        productDTO.setProductPrice(20000);
        productDTO.setProductId(1L);
    }

    @Test
    void insertDnBoard() {
        // given
        dnBoardMapper.insertDnBoard(dnBoardWriteDTO);
        dnBoardMapper.insertProduct(productDTO);
        // when

        // then


    }

    @Test
    void insertProduct() {
    }
}