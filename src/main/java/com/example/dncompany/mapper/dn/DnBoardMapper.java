package com.example.dncompany.mapper.dn;

import com.example.dncompany.dto.dn.DnBoardDetailDTO;
import com.example.dncompany.dto.dn.DnBoardListDTO;
import com.example.dncompany.dto.dn.DnBoardWriteDTO;
import com.example.dncompany.dto.dn.ProductDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;


@Mapper
public interface DnBoardMapper {
    void insertDnBoard (DnBoardWriteDTO dnBoardWriteDTO);

    Optional<DnBoardDetailDTO> selectDnBoardById (Long dnId);

    List<DnBoardListDTO> selectAllDnBoardList ();

}

