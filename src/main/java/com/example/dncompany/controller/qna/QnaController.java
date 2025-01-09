package com.example.dncompany.controller.qna;

import com.example.dncompany.dto.qna.QnADTO;
import com.example.dncompany.dto.qna.QnADetailDTO;
import com.example.dncompany.service.qna.QnaService;
import com.example.dncompany.service.zip.ZipService;
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
    private final ZipService zipService;

    // 전체글
    @GetMapping("/list")
    public String list(Model model) {
        List<QnADTO> qnaList = qnaService.getAllQnaBoards();
        model.addAttribute("qnaList", qnaList);
        return "qna/list";
    }

    // 상세글
    @GetMapping("/detail")
    public String detail(Long qnaId, Model model) {

        QnADetailDTO foundQnA = qnaService.getQnAById(qnaId);
        model.addAttribute("qna", foundQnA);

        return "qna/detail";
    }

    // 게시글 작성
    @GetMapping("/write")
    public String write() {
        return "qna/write";
    }
}
