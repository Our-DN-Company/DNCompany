package com.example.dncompany.controller.qna;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/qna")
@RequiredArgsConstructor
public class QnaController {

    @GetMapping("/list")
    public String list() {return "qna/list";}

    @GetMapping("/detail")
    public String detail() {return "qna/detail";}

    @GetMapping("/write")
    public String write() {return "qna/write";}
}
