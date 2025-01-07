package com.example.dncompany.service.user;


import com.example.dncompany.mapper.user.MypageMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor

public class MypageService {
    private final MypageMapper mypageMapper;


}
