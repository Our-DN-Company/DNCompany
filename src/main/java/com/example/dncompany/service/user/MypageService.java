package com.example.dncompany.service.user;

import com.example.dncompany.dto.user.mypage.AddPetDTO;
import com.example.dncompany.exception.user.PetCalculateException;
import com.example.dncompany.mapper.user.MypageMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class MypageService {
    private final MypageMapper mypageMapper;



}
