package com.example.dncompany.controller.zip;

import com.example.dncompany.dto.zip.ZipBoardDetailDTO;
import com.example.dncompany.dto.zip.ZipBoardListDTO;
import com.example.dncompany.service.zip.ZipService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/zip")
@RequiredArgsConstructor
public class ZipController {
    private final ZipService zipService;

    @GetMapping("/community")
    public String community(Model model) {
        List<ZipBoardListDTO> boardList = zipService.getAllZipBoards();
        model.addAttribute("boardList", boardList);
        return "zip/community";
    }

    @GetMapping("/detail")
    public String detail(Long zipId, Model model) {

        ZipBoardDetailDTO foundZip = zipService.getZipBoardById(zipId);
        model.addAttribute("board", foundZip);

        return "zip/detail";
    }

    @GetMapping("/write")
    public String write() {
        return "zip/write";
    }

}























