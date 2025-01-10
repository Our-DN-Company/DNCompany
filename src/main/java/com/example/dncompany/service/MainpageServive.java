package com.example.dncompany.service;

import com.example.dncompany.dto.MainDnDTO;
import com.example.dncompany.mapper.MainpageMapper;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;


@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class MainpageServive {
    private final MainpageMapper mainpageMapper;

    @Value("C:/upload/free")
    private String uploadPath;

    public List<MainDnDTO> addSelectDnBoard(){
        return mainpageMapper.selectMainpageDnboard4Post();
    }

}
