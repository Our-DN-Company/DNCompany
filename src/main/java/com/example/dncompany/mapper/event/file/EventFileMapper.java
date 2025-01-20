package com.example.dncompany.mapper.event.file;

import com.example.dncompany.dto.event.file.EventFileDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.Optional;

@Mapper
public interface EventFileMapper {
    void insertFile (EventFileDTO eventFileDTO);

    Optional<EventFileDTO> selectByEventId (Long eventId);

    void deleteByEventId (Long eventId);
}
