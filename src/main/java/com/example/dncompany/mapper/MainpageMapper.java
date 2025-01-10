package com.example.dncompany.mapper;

import com.example.dncompany.dto.MainDnDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MainpageMapper {

//    List<MainDnDTO> selectMainpageDnboardPost(MainDnDTO mainDnDTO);

    List<MainDnDTO> selectMainpageDnboard4Post();
}
