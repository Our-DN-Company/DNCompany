package com.example.dncompany.mapper.dn;

import com.example.dncompany.dto.dn.file.DnFileDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DnFileMapper {

    void insertFile(DnFileDTO dnFileDTO);

}
