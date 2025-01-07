package com.example.dncompany.mapper.user;


import com.example.dncompany.dto.user.mypage.AddPetDTO;
import com.example.dncompany.dto.user.mypage.PetSlideDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;


@Mapper
public interface MypageMapper {

 void addPet(AddPetDTO addPetDTO);

 List<PetSlideDTO> petSlide();

}
