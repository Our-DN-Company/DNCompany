package com.example.dncompany.mapper.user;

import com.example.dncompany.dto.user.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class UserMapperTest {

    @Autowired
    UserMapper userMapper;

    UserJoinDTO userJoinDTO;

    UserJoinKakaoDTO userJoinKakaoDTO;

    UserUpdateKakaoDTO userUpdateKakaoDTO = new UserUpdateKakaoDTO();


    @BeforeEach
    void setUp() {
//        userJoinDTO = new UserJoinDTO();
//        userJoinDTO.setLoginId("test");
//        userJoinDTO.setPassword("1234");
//        userJoinDTO.setGender("M");
//        userJoinDTO.setEmail("test@gmail.com");
//        userJoinDTO.setName("test");
//        userJoinDTO.setPhoneNumber("01012341234");
//        userJoinDTO.setAddress("test");
//        userJoinDTO.setAddressDetail("test");
//        userJoinDTO.setZipCode("123456");
        // 존재하는 USERS_ID로 설정

    }

//    @Test
//    @DisplayName("회원 정보 삽입, 로그인 정보 조회")
//    void insertMember_And_selectLoginInfo() {
//        // given
//        UserLoginDTO userLoginDTO = new UserLoginDTO();
//        userLoginDTO.setLoginId("test");
//        userLoginDTO.setPassword("1234");
//        // when
//        userMapper.insertUser(userJoinDTO);
//        UserSessionDTO memberSessionDTO = userMapper.selectLoginInfo(userLoginDTO)
//                .orElse(null);
//        // then
//        assertThat(memberSessionDTO)
//                .isNotNull()
//                .extracting("usersId", "loginId", "role")
//                .containsExactly( userJoinDTO.getUsersId(), "test", "ROLE_USER");
//
//    }
//
//    @Test
//    void countByLoginId() {
//        // given
//        userMapper.insertUser(userJoinDTO);
//        // when
//        int count = userMapper.countByLoginId(userJoinDTO.getLoginId());
//        // then
//        assertThat(count).isEqualTo(1);
//    }
//
//    @Test
//    void insertKakaoIdUsers() {
//        userJoinKakaoDTO = new UserJoinKakaoDTO();
//        userJoinKakaoDTO.setUsersId(100L);
//        userJoinKakaoDTO.setKakaoId(123456L);
//
//        userMapper.insertKakaoIdUsers(userJoinKakaoDTO);
//
//
//
//
//
//    }
//
//    @Test
//    void kakaoisduplicateUsersId() {
//        userMapper.kakaoisduplicateUsersId(123456L);
//    }

    @Test
    @DisplayName("카카오톡 로그인한 회원 업데이트 검증 및 조회")
    void updateKakaoLoginUser() {
        // 업데이트할 DTO 생성

        userUpdateKakaoDTO.setUsersId(145L); // 존재하는 테스트 데이터 ID
        userUpdateKakaoDTO.setLoginId("yyy1234");
        userUpdateKakaoDTO.setPassword("1234");
        userUpdateKakaoDTO.setName("yyytest");
        userUpdateKakaoDTO.setEmail("yyy@gmail.com");
        userUpdateKakaoDTO.setPhoneNumber("1234567890");
        userUpdateKakaoDTO.setZipCode("123456");
        userUpdateKakaoDTO.setAddress("123456");
        userUpdateKakaoDTO.setAddressDetail("123456");
        userUpdateKakaoDTO.setGender("M");
        userUpdateKakaoDTO.setBirthDate(LocalDate.parse("2025-01-01"));

        // 업데이트 검증
        userMapper.updateKakaoLoginUser(userUpdateKakaoDTO);

        // 업데이트 결과 조회 및 검증
         userMapper.selectAllFromUser(145L); // 결과 조회 메서드 호출



    }
}




























