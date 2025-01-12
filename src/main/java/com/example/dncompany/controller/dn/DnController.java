package com.example.dncompany.controller.dn;

import com.example.dncompany.dto.dn.DnBoardWriteDTO;
import com.example.dncompany.dto.dn.ProductDTO;
import com.example.dncompany.service.dn.DnBoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

@Controller
@RequestMapping("/dn")
@RequiredArgsConstructor
public class DnController {
    private final DnBoardService dnBoardService;

    @GetMapping("/market")
    public String market() {return "dn/market";}

    @GetMapping("/detail")
    public String detail() {return "dn/detail";}

    @GetMapping("/write")
    public String write() {
        return "dn/write";
    }

    @PostMapping("/write")
    public String write(DnBoardWriteDTO dnBoardWriteDTO,
                        ProductDTO productDTO, @SessionAttribute("usersId") Long usersId) {

        dnBoardService.addDnBoard(dnBoardWriteDTO,productDTO,usersId);

        return "redirect:/dn/market";
    }

    @GetMapping("/modify")
    public String modify(){
        return "dn/modify";
    }

}
