package com.example.dncompany.mapper;

import com.example.dncompany.dto.MainDnDTO;
import com.example.dncompany.dto.MainEventDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MainpageMapper {

    List<MainDnDTO> selectMainpageDnboard4Post();

    List<MainEventDTO> selectMainpageEventboard2Post();
}
