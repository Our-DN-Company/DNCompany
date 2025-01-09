package com.example.dncompany.controller.help;

import com.example.dncompany.dto.help.HelpListDTO;
import com.example.dncompany.service.help.HelpService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/help")
@RequiredArgsConstructor
public class HelpController {
    private final HelpService helpService;

    @GetMapping("/write")
    public String helpWrite(){
        return "help/write";
    }

    @GetMapping("/list")
    public String helpList(Model model){
        List<HelpListDTO> helpList = helpService.getHelpList();
        model.addAttribute("helpList", helpList);
        return "help/list";
    }

    @GetMapping("/detail")
    public String helpDetail(){
        return "help/detail";
    }
}
