package com.example.dncompany.mapper.user;


import com.example.dncompany.dto.user.mypage.AddPetDTO;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface MypageMapper {

 void addPet(AddPetDTO addPetDTO);



}
