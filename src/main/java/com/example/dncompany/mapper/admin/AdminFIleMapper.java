package com.example.dncompany.mapper.admin;


import com.example.dncompany.dto.admin.board.file.AdminFIleDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AdminFIleMapper {

    void insertEventBoard (AdminFIleDTO adminFIleDTO) ;

    List<AdminFIleDTO>  selectOldEventFileDTO ();


}
