package com.example.dncompany.controller.dn;

import com.example.dncompany.dto.dn.*;
import com.example.dncompany.service.dn.DnBoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/dn")
@RequiredArgsConstructor
public class DnController {
    private final DnBoardService dnBoardService;

    @GetMapping("/market")
    public String market(Model model) {
        List<DnBoardListDTO> dnBoardList = dnBoardService.getDnBoardList();
        model.addAttribute("dnBoardList", dnBoardList);

        return "dn/market";
    }

    @GetMapping("/detail")
    public String detail(Long dnId, Model model) {

        DnBoardDetailDTO foundBoard = dnBoardService.getDnBoardById(dnId);
        model.addAttribute("board",foundBoard);

        return "dn/detail";
    }

    @GetMapping("/write")
    public String write() {
        return "dn/write";
    }

    @PostMapping("/write")
    public String write(DnBoardWriteDTO dnBoardWriteDTO,
                        ProductDTO productDTO,
                        DnSellBoardDTO dnSellBoardDTO,
                        @SessionAttribute("usersId") Long usersId) {

        dnBoardService.addDnBoard(dnBoardWriteDTO,productDTO,usersId);

        return "redirect:/dn/market";
    }

    @GetMapping("/modify")
    public String modify(Long dnId, Model model){
        DnBoardDetailDTO foundBoard = dnBoardService.getDnBoardById(dnId);
        model.addAttribute("board", foundBoard);

        return "dn/modify";
    }

    @PostMapping("/modify")
    public String modify(DnBoardModifyDTO boardModifyDTO,
                         ProductModifyDTO productModifyDTO,
                         RedirectAttributes redirectAttributes){

        dnBoardService.modifyDnBoard(boardModifyDTO,productModifyDTO);

        redirectAttributes.addAttribute("dnId",boardModifyDTO.getDnId());
        redirectAttributes.addAttribute("productId",productModifyDTO.getProductId());

        return "redirect:/dn/detail";
    }

    @GetMapping("/delete")
    public String delete(Long dnId, Long productId){
        dnBoardService.removeDnBoard(dnId,productId);
        return "redirect:/dn/market";
    }
}
