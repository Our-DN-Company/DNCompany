package com.example.dncompany.mapper.dn;

import com.example.dncompany.dto.dn.DnBoardDTO;
import com.example.dncompany.dto.dn.ProductDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DnMapper {
    void insertDnBoard (DnBoardDTO dnBoardDTO);

    void insertProduct (ProductDTO productDTO);

}

