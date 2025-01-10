package com.example.dncompany.controller.zip;

import com.example.dncompany.dto.zip.ZipBoardDetailDTO;
import com.example.dncompany.dto.zip.ZipBoardListDTO;
import com.example.dncompany.dto.zip.ZipBoardWriteDTO;
import com.example.dncompany.service.zip.ZipService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
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
//        session.setAttribute("usersId", 6L);
        ZipBoardDetailDTO foundZip = zipService.getZipBoardById(zipId);
        model.addAttribute("board", foundZip);

        return "zip/detail";
    }

    // 게시글 작성
    @GetMapping("/write")
    public String write() {
        return "zip/write";
    }

    @PostMapping("/write")
    public String write(ZipBoardWriteDTO zipBoardWriteDTO,
//                        HttpSession session,
                        @SessionAttribute(value = "usersId", required = false) Long usersId) {
        log.info("write zipBoardWriteDTO: {}", zipBoardWriteDTO);
//        Long usersId = (Long) session.getAttribute("usersId");
        usersId = 6L;

        zipService.addZipBoard(zipBoardWriteDTO, usersId);
        return "redirect:/zip/community";
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























