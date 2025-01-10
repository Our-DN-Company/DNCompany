package com.example.dncompany.controller;

import com.example.dncompany.dto.MainDnDTO;
import com.example.dncompany.mapper.MainpageMapper;
import com.example.dncompany.service.MainpageServive;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class HomeController {


    private final MainpageServive mainpageServive;

    @GetMapping("/")
    public String mainPage(Model model) {
        List<MainDnDTO> maindnDTOList = mainpageServive.addSelectDnBoard();
        model.addAttribute("maindnDTOList", maindnDTOList);

        return "mainPage";
    }
}
