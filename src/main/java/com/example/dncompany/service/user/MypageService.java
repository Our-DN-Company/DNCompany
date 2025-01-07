package com.example.dncompany.service.user;

import com.example.dncompany.dto.user.mypage.AddPetDTO;
import com.example.dncompany.dto.user.mypage.PetSlideDTO;
import com.example.dncompany.exception.user.PetCalculateException;
import com.example.dncompany.mapper.user.MypageMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;


@Service
@RequiredArgsConstructor

public class MypageService {
    private final MypageMapper mypageMapper;

    public List<PetSlideDTO> getPetSlideById() {
        return mypageMapper.petSlide();
    }

}
