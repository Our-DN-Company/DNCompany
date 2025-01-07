package com.example.dncompany.service.user;


import com.example.dncompany.dto.user.mypage.AddPetDTO;
import com.example.dncompany.dto.user.mypage.PetSlideDTO;
import com.example.dncompany.dto.user.mypage.UserProfileDTO;
import com.example.dncompany.exception.user.UserNotFoundException;
import com.example.dncompany.mapper.user.MypageMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
@Transactional
@RequiredArgsConstructor
public class MypageService {
    private final MypageMapper mypageMapper;

    public void addPet(AddPetDTO addPetDTO) {
        mypageMapper.insertPet(addPetDTO);
    }

    public List<PetSlideDTO> getPetSlide(Long usersId){
        return mypageMapper.selectPetList(usersId);
    }

    public  UserProfileDTO userProfile(Long usersId) {
       return  mypageMapper.userProfile(usersId)
              .orElseThrow(()->new UserNotFoundException("존재하지 않는 회원 번호 입니다"));
    }
}
