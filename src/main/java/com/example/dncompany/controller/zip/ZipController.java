package com.example.dncompany.controller.zip;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/zip")
@RequiredArgsConstructor
public class ZipController {

    @GetMapping("/list")
    public String list() {return "zip/community";}

    @GetMapping("/detail")
    public String detail() {return "zip/detail";}

    @GetMapping("/write")
    public String write() {return "zip/write";}

}
