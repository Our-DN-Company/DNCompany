package com.example.dncompany.mapper.admin;


import com.example.dncompany.dto.admin.board.file.AdminFIleDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AdminFIleMapper {

    void insertEventBoard (AdminFIleDTO adminFIleDTO) ;

}
