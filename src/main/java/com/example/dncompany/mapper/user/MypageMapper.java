package com.example.dncompany.mapper.user;


import com.example.dncompany.dto.user.mypage.AddPetDTO;

import com.example.dncompany.dto.user.mypage.HelpMeListDTO;
import com.example.dncompany.dto.user.mypage.PetSlideDTO;
import com.example.dncompany.dto.user.mypage.UserProfileDTO;
import org.apache.ibatis.annotations.Mapper;


import java.util.List;
import java.util.Optional;


@Mapper
public interface MypageMapper {

    void insertPet(AddPetDTO addPetDTO);

    List<PetSlideDTO> selectPetList(Long usersId);

    Optional<UserProfileDTO> userProfile(Long usersId);

    List<HelpMeListDTO> helpMeListById(Long usersId);

}
