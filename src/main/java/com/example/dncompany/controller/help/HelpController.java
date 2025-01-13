package com.example.dncompany.controller.help;

import com.example.dncompany.dto.help.HelpListDTO;
import com.example.dncompany.dto.help.HelpDetailDTO;
import com.example.dncompany.dto.help.HelpSearchDTO;
import com.example.dncompany.dto.help.HelpWriteDTO;
import com.example.dncompany.dto.help.pet.HelpPetListDTO;
import com.example.dncompany.service.help.HelpService;
import com.example.dncompany.service.help.pet.HelpPetService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/help")
@RequiredArgsConstructor
public class HelpController {
    private final HelpService helpService;
    private final HelpPetService helpPetService;

    @GetMapping("/write")
    public String helpWrite(@SessionAttribute(value = "usersId", required = false) Long usersId,
                            Model model) {
      //  usersId = 6L; // 임시 처리
        if (usersId==null){
            return "/user/login";
        }

        List<HelpPetListDTO> petList = helpPetService.getPetListByUsersId(usersId);
        model.addAttribute("petList", petList);

        return "help/write";
    }

    @PostMapping("/write")
    public String helpWrite(@SessionAttribute(value = "usersId", required = false) Long usersId,
                            HelpWriteDTO helpWriteDTO) {
        log.info("helpWriteDTO: {}", helpWriteDTO);
      //  usersId = 6L; // 임시 처리

        helpService.registerHelp(helpWriteDTO, usersId);

        return "redirect:/help/list";
    }

@GetMapping("/list")
public String helpList(@SessionAttribute(value = "usersId", required = false) Long usersId,
                       Model model) {
    // 로그인 체크
    if (usersId == null) {
        return "/user/login";
    }

    List<HelpListDTO> helpList = helpService.getHelpList();
    model.addAttribute("helpList", helpList);
    return "help/list";
}

    @GetMapping("/detail")
    public String helpDetail(@RequestParam Long helpId, Model model) {
        HelpDetailDTO helpDetail = helpService.getHelpDetail(helpId);
        log.info("helpDetail: {}", helpDetail);
        model.addAttribute("helpDetail", helpDetail);
        return "help/detail";


    }
    @GetMapping("/search")
    public String searchHelp(@ModelAttribute HelpSearchDTO searchDTO, Model model) {
        log.info("검색 요청 DTO: {}", searchDTO); // 검색 조건 로그

        List<HelpListDTO> searchResult = helpService.searchHelpList(searchDTO);
        log.info("검색 결과 개수: {}", searchResult.size()); // 결과 개수 로그

        model.addAttribute("helpList", searchResult);
        model.addAttribute("searchDTO", searchDTO);
        return "help/list";
    }
}














