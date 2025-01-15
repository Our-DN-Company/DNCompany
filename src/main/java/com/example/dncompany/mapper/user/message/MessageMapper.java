package com.example.dncompany.mapper.user.message;

import com.example.dncompany.dto.page.PageRequestDTO;
import com.example.dncompany.dto.user.message.MessagePageDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MessageMapper {

        List<MessagePageDTO> selectFromMessage(Long usersId);
        List<MessagePageDTO> selectToMessage(Long usersId);

        List<MessagePageDTO> selectToMessagePage(@Param("page") PageRequestDTO pageRequestDTO, @Param("userTo") Long userTo);
        List<MessagePageDTO> selectFromMessagePage(@Param("page") PageRequestDTO pageRequestDTO, @Param("userFrom") Long userFrom);

        int countByTotalFrom(@Param("userFrom") Long userFrom);
        int countByTotalTo(@Param("userTo") Long userTo);


}
