package com.example.dncompany.mapper.dn;

import com.example.dncompany.dto.dn.file.DnFileDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.Optional;

@Mapper
public interface DnFileMapper {

    void insertFile(DnFileDTO dnFileDTO);

    Optional<DnFileDTO> selectByBoardId(Long dnId);

}
