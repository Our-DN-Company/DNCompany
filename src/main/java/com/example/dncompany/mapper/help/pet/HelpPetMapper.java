package com.example.dncompany.mapper.help.pet;

import com.example.dncompany.dto.help.pet.HelpPetListDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface HelpPetMapper {
    List<HelpPetListDTO> selectByUsersId(Long usersId);
}
