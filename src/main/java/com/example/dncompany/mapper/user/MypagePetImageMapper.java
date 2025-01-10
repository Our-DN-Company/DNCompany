package com.example.dncompany.mapper.user;

import com.example.dncompany.dto.user.mypage.PetImageDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.Optional;

@Mapper
public interface MypagePetImageMapper {

    void insertPetImg(PetImageDTO petImageDTO);

    void deleteByPetId(Long petId);

}
