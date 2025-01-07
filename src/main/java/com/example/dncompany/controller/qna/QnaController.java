package com.example.dncompany.controller.qna;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/qna")
@RequiredArgsConstructor
public class QnaController {

    @GetMapping("/QNACommunity")
    public String QNACommunity() {return "qna/QNACommunity";}

    @GetMapping("/QNADetail")
    public String QNADetail() {return "qna/QNADetail";}

    @GetMapping("/QNAWrite")
    public String QNAWrite() {return "qna/QNAWrite";}
}
