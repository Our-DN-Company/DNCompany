package com.example.dncompany.controller.zip;

import com.example.dncompany.dto.zip.ZipBoardDetailDTO;
import com.example.dncompany.dto.zip.ZipBoardListDTO;
import com.example.dncompany.dto.zip.ZipBoardWriteDTO;
import com.example.dncompany.dto.page.PageDTO;
import com.example.dncompany.dto.page.PageRequestDTO;
import com.example.dncompany.dto.zip.zipPage.ZipBoardSearchDTO;
import com.example.dncompany.service.zip.ZipService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@RequestMapping("/zip")
@RequiredArgsConstructor
public class ZipController {
    private final ZipService zipService;

    // 전체글
    @GetMapping("/community")
    public String community(ZipBoardSearchDTO searchDTO,
                            PageRequestDTO pageRequestDTO,
                            Model model) {

        if (searchDTO.getSearchType() == null){
            searchDTO.setSearchType("title");
        }
        if (searchDTO.getKeyword() == null){
            searchDTO.setKeyword("");
        }



//        List<ZipBoardListDTO> boardList = zipService.getAllZipBoards();
        PageDTO<ZipBoardListDTO> pageDTO = zipService.getZipBoardsBySearchCondWithPage(searchDTO, pageRequestDTO);

//        model.addAttribute("boardList", boardList);
        model.addAttribute("pageDTO", pageDTO);
        model.addAttribute("searchDTO", searchDTO);

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
                        HttpSession session,
                        @SessionAttribute(value = "usersId", required = false) Long usersId) {
        log.info("write zipBoardWriteDTO: {}", zipBoardWriteDTO);
        Long usersId1 = (Long) session.getAttribute("usersId");

        zipService.addZipBoard(zipBoardWriteDTO, usersId1);
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























