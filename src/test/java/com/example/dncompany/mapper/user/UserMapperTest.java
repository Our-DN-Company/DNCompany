package com.example.dncompany.mapper.user;

import com.example.dncompany.dto.user.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserMapperTest {

    @Autowired
    UserMapper userMapper;

    UserJoinDTO userJoinDTO;

    UserJoinKakaoDTO userJoinKakaoDTO;

    @BeforeEach
    void setUp() {
        userJoinDTO = new UserJoinDTO();
        userJoinDTO.setLoginId("test");
        userJoinDTO.setPassword("1234");
        userJoinDTO.setGender("M");
        userJoinDTO.setEmail("test@gmail.com");
        userJoinDTO.setName("test");
        userJoinDTO.setPhoneNumber("01012341234");
        userJoinDTO.setAddress("test");
        userJoinDTO.setAddressDetail("test");
        userJoinDTO.setZipCode("123456");
    }

    @Test
    @DisplayName("회원 정보 삽입, 로그인 정보 조회")
    void insertMember_And_selectLoginInfo() {
        // given
        UserLoginDTO userLoginDTO = new UserLoginDTO();
        userLoginDTO.setLoginId("test");
        userLoginDTO.setPassword("1234");
        // when
        userMapper.insertUser(userJoinDTO);
        UserSessionDTO memberSessionDTO = userMapper.selectLoginInfo(userLoginDTO)
                .orElse(null);
        // then
        assertThat(memberSessionDTO)
                .isNotNull()
                .extracting("usersId", "loginId", "role")
                .containsExactly( userJoinDTO.getUsersId(), "test", "ROLE_USER");

    }

    @Test
    void countByLoginId() {
        // given
        userMapper.insertUser(userJoinDTO);
        // when
        int count = userMapper.countByLoginId(userJoinDTO.getLoginId());
        // then
        assertThat(count).isEqualTo(1);
    }

    @Test
    void insertKakaoIdUsers() {
        userJoinKakaoDTO = new UserJoinKakaoDTO();
        userJoinKakaoDTO.setUsersId(100L);
        userJoinKakaoDTO.setKakaoId(123456L);

        userMapper.insertKakaoIdUsers(userJoinKakaoDTO);





    }
}