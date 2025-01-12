package com.example.dncompany.mapper.user;

import com.example.dncompany.dto.user.message.MessagePageDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MessageMapper {
    List<MessagePageDTO> selectFromMessage(long usersId);
    List<MessagePageDTO> selectToMessage(long usersId);
}
