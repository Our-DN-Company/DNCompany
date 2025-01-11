package com.example.dncompany.mapper.dn;

import com.example.dncompany.dto.dn.DnBoardWriteDTO;
import com.example.dncompany.dto.dn.ProductDTO;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface DnBoardMapper {
    void insertDnBoard (DnBoardWriteDTO dnBoardWriteDTO);

    void insertProduct (ProductDTO productDTO);

}

