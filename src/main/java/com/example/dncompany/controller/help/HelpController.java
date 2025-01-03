package com.example.dncompany.controller.help;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/help")
@RequiredArgsConstructor
public class HelpController {

    @GetMapping("/helpwrite")
    public String helpwrite(){
        return "help/helpwrite";
    }
    @GetMapping("/helplist")
    public String helplist(){
        return "help/helplist";
    }
    @GetMapping("/helpdetail")
    public String helpdetail(){
        return "help/helpdetail";
    }
}
