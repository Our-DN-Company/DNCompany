package com.example.dncompany.controller.dn;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/dn")
@RequiredArgsConstructor
public class DnController {

    @GetMapping("/marketboard")
    public String marketboard() {return "dn/marketboard";}

    @GetMapping("/detail")
    public String detail() {return "dn/detail";}

    @GetMapping("/write")
    public String write() {return "dn/write";}

}
