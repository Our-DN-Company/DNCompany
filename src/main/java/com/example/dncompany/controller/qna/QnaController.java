package com.example.dncompany.controller.qna;

import com.example.dncompany.dto.qna.QnADTO;
import com.example.dncompany.service.qna.QnaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


@Controller
@RequestMapping("/qna")
@RequiredArgsConstructor
public class QnaController {

    private final QnaService qnaService;

    @GetMapping("/list")
    public String list(Model model) {
        List<QnADTO> qnaList = qnaService.getAllQnaBoards();
        model.addAttribute("qnaList", qnaList);
        return "qna/list";
    }

    @GetMapping("/detail")
    public String detail() {
        return "qna/detail";
    }

    @GetMapping("/write")
    public String write() {
        return "qna/write";
    }
}
