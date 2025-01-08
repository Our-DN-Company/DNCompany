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

    // 전체글
    @GetMapping("/community")
    public String community(Model model) {
        List<ZipBoardListDTO> boardList = zipService.getAllZipBoards();
        model.addAttribute("boardList", boardList);
        return "zip/community";
    }

    // 상세글
    @GetMapping("/detail")
    public String detail(Long zipId, Model model) {

        ZipBoardDetailDTO foundZip = zipService.getZipBoardById(zipId);
        model.addAttribute("board", foundZip);

        return "zip/detail";
    }

    // 게시글 작성
    @GetMapping("/write")
    public String write() {
        return "zip/write";
    }

    // 게시글 수정
    @GetMapping("/modify")
    public String modify() {
        return "zip/modify";
    }

    // 게시글 삭제
    @GetMapping("/delete")
    public String delete(Long zipId) {
        zipService.removeZipBoard(zipId);
        return "redirect:zip/community";
    }

}























