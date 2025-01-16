package com.example.dncompany.mapper.dn;

import com.example.dncompany.dto.dn.*;
import com.example.dncompany.dto.page.PageDTO;
import com.example.dncompany.dto.page.PageRequestDTO;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
@Transactional
class DnBoardMapperTest {

    @Autowired
    private DnBoardMapper dnBoardMapper;

    @Autowired
    private DnProductMapper dnProductMapper;

    DnBoardDetailDTO dnBoardDetailDTO;
    DnBoardWriteDTO dnBoardWriteDTO;
    ProductDTO productDTO;
    DnSellBoardDTO dnSellBoardDTO;
    DnSearchDTO dnSearchDTO;
    PageDTO<DnBoardListDTO> dnBoardListDTO;
    PageRequestDTO pageRequestDTO;

    @BeforeEach
    void setUp() {
        dnBoardWriteDTO = new DnBoardWriteDTO();
        productDTO = new ProductDTO();
        dnSellBoardDTO = new DnSellBoardDTO();
        dnSearchDTO = new DnSearchDTO();
        dnBoardWriteDTO.setUsersId(6L);
        dnBoardWriteDTO.setDnId(1L);
        dnBoardWriteDTO.setDnTitle("테스트용 타이틀이다~~");
        dnBoardWriteDTO.setDnContent("테스트용 내용 작성!!");
        dnBoardWriteDTO.setDnPetCategory("강아지");
        productDTO.setProductCategory("간식");
        productDTO.setProductPrice(20000);
        productDTO.setProductId(1L);
        dnSellBoardDTO.setDnId(dnBoardWriteDTO.getDnId());
        dnSellBoardDTO.setUsersId(dnBoardWriteDTO.getUsersId());
        dnSellBoardDTO.setProductId(productDTO.getProductId());
        dnSearchDTO.setOrder("price-desc-order");
        dnSearchDTO.setKeyword("a");
        dnSearchDTO.setProductCategory("snack");
        dnSearchDTO.setDnPetCategory("dog");
        dnSearchDTO.setSearchType("usersId");
    }

    @Test
    @DisplayName("데이터 삽입 및 데이터 한 건 조회 테스트")
    void insertAndSelectByIdDnBoard() {
        // given
        dnBoardMapper.insertDnBoard(dnBoardWriteDTO);
        dnProductMapper.insertProduct(productDTO);
        dnBoardMapper.insertSellBoard(dnSellBoardDTO);

        // when
        DnBoardDetailDTO foundDnBoard = dnBoardMapper.selectDnBoardById(dnBoardWriteDTO.getDnId()).orElse(null);

        // then
        assertThat(foundDnBoard).isNotNull()
                .extracting("dnTitle")
                .isEqualTo("테스트용 타이틀이다~~");
        assertThat(foundDnBoard).isNotNull()
                .extracting("productCategory")
                .isEqualTo("간식");

    }



    @Test
    void selectAllDnBoardList() {
        //given
        dnBoardMapper.insertDnBoard(dnBoardWriteDTO);
        dnProductMapper.insertProduct(productDTO);
        //when
        List<DnBoardListDTO> boardList = dnBoardMapper.selectAllDnBoardList();
        //then
        assertThat(boardList)
                .isNotEmpty()
                .extracting("dnTitle")
                .contains("테스트용 타이틀이다~~");
    }

    @Test
    @DisplayName("전체 조회 한 뒤 검색 조건 추가 테스트")
    void selectAllDnBoardListCondWithPage() {
        //given
        dnBoardMapper.insertDnBoard(dnBoardWriteDTO);
        dnProductMapper.insertProduct(productDTO);
        dnBoardMapper.countBySearchCondition(dnSearchDTO);
        //when
        List<DnBoardListDTO> boardList = dnBoardMapper.selectAllDnBoardListCondWithPage(pageRequestDTO, dnSearchDTO);
        //then
        assertThat(boardList)
                .isNotEmpty()
                .extracting("dnTitle")
                .contains("테스트용 타이틀이다~~");
        assertThat(boardList).isNotNull()
                .extracting("productCategory")
                .isEqualTo("snack");
        assertThat(boardList).isNotNull()
                .extracting("dnPetCategory")
                .isEqualTo("dog");

    }
}