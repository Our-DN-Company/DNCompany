package com.example.dncompany.mapper.dn;

import com.example.dncompany.dto.dn.DnBoardWriteDTO;
import com.example.dncompany.dto.dn.ProductDTO;
import com.example.dncompany.dto.dn.file.DnFileDTO;
import com.example.dncompany.dto.user.UserJoinDTO;
import com.example.dncompany.mapper.user.UserMapper;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
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

    @Autowired
    UserMapper userMapper;

    DnFileDTO dnfileDTO;

    @BeforeEach
    void setUp() {
        UserJoinDTO userJoinDTO = new UserJoinDTO();
        userJoinDTO.setLoginId("test");
        userJoinDTO.setPassword("test");
        userJoinDTO.setName("test");
        userJoinDTO.setEmail("test@test.com");
        userJoinDTO.setPhoneNumber("01012344567");
        userJoinDTO.setZipCode("56455");
        userJoinDTO.setAddress("test");
        userJoinDTO.setAddressDetail("test");
        userJoinDTO.setGender("M");
        userJoinDTO.setBirthDate(LocalDate.ofEpochDay(2021-01-01));

        userMapper.insertUser(userJoinDTO);

        DnBoardWriteDTO dnBoardWriteDTO = new DnBoardWriteDTO();
        dnBoardWriteDTO.setUsersId(userJoinDTO.getUsersId());
        dnBoardWriteDTO.setDnTitle("test title");
        dnBoardWriteDTO.setDnId(1L);
        dnBoardWriteDTO.setDnPetCategory("test");
        dnBoardWriteDTO.setDnContent("test");
        dnBoardMapper.insertDnBoard(dnBoardWriteDTO);

        ProductDTO dnProductDTO = new ProductDTO();
        dnProductDTO.setProductId(1L);
        dnProductDTO.setProductPrice(20000);
        dnProductDTO.setProductCategory("test");
        dnProductMapper.insertProduct(dnProductDTO);

        dnfileDTO = new DnFileDTO();
        dnfileDTO.setProductId(dnProductDTO.getProductId());
        dnfileDTO.setProductUuid("test");
        dnfileDTO.setProductOriginalFilename("test");
        dnfileDTO.setProductPath("test");
        dnfileDTO.setProductExtension("test");
        dnfileDTO.setProductImgId(1L);

    }

    // file 테스트 진행중
    // insert file을 할 때

    @Test
    void insertFile_And_SelectById() {
        // given
        dnFileMapper.insertFile(dnfileDTO);
        // when
        DnFileDTO foundFile = dnFileMapper.selectByBoardId(dnfileDTO.getProductId())
                .orElse(null);
        // then
        assertThat(foundFile).isNotNull()
                .extracting("productUuid")
                .isEqualTo("test");
    }
}