package com.example.dncompany.mapper.user;

import com.example.dncompany.dto.user.UserJoinDTO;
import com.example.dncompany.dto.user.UserLoginDTO;
import com.example.dncompany.dto.user.UserSessionDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.Optional;

@Mapper
public interface UserMapper {
    void insertUser (UserJoinDTO userJoinDTO);

    Optional<UserSessionDTO> selectLoginInfo(UserLoginDTO userLoginDTO);

    int countByLoginId(String LoginId);
}
