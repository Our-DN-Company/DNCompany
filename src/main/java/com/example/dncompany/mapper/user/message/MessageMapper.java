package com.example.dncompany.mapper.user.message;

import com.example.dncompany.dto.page.PageRequestDTO;
import com.example.dncompany.dto.user.message.MessagePageDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MessageMapper {

        List<MessagePageDTO> selectFromMessage(long usersId);
        List<MessagePageDTO> selectToMessage(long usersId);

        List<MessagePageDTO> selectToMessagePage(@Param("page") PageRequestDTO pageRequestDTO);
        List<MessagePageDTO> selectFromMessagePage(@Param("page") PageRequestDTO pageRequestDTO);

        int countByPage(@Param("userFrom") Long userFrom, @Param("userTo") Long userTo);


}
