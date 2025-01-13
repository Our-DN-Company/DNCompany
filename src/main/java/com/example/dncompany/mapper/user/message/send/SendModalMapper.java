package com.example.dncompany.mapper.user.message.send;

import com.example.dncompany.dto.user.message.MessageSendDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SendModalMapper {
    void insertSendModal(MessageSendDTO messageSendDTO);

    long selectloginIdUserTo(String loginIdTo);

}
