package com.example.dncompany.mapper.user;

import com.example.dncompany.dto.user.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface UserMapper {
    void insertUser (UserJoinDTO userJoinDTO);

    Optional<UserSessionDTO> selectLoginInfo(UserLoginDTO userLoginDTO);

    int countByLoginId(String LoginId);

    void insertKakaoIdUsers (UserJoinKakaoDTO userJoinKakaoDTO);

    Optional<UserSessionDTO> kakaoisduplicateUsersId(Long kakaoId);

    void updateKakaoLoginUser(UserUpdateKakaoDTO userUpdateKakaoDTO);

    List<UserUpdateKakaoDTO> selectAllFromUser(Long usersId);
}
