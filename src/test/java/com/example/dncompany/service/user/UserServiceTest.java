package com.example.dncompany.service.user;

import com.example.dncompany.dto.user.UserJoinDTO;
import com.example.dncompany.exception.user.UserDuplicateException;
import com.example.dncompany.mapper.user.UserMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    @Mock
    UserMapper userMapper;

    @InjectMocks
    UserService userService;

    @Test
    void addUser() {
        // given
        when(userMapper.countByLoginId(any())).thenReturn(0);
        // when
        userService.addUser(new UserJoinDTO());
        // then
        verify(userMapper).countByLoginId(any());
        verify(userMapper).insertUser(any());

    }
    @Test
    void addUser_Exception() {
        when(userMapper.countByLoginId(any())).thenReturn(1);

        //when
        //then
        assertThatThrownBy(() -> userService.addUser(new UserJoinDTO()))
                .isInstanceOf(UserDuplicateException.class)
                .hasMessageContaining("이미 사용중인");
    }
}