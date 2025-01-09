package com.example.dncompany.controller.user;

import com.example.dncompany.dto.user.mypage.AddPetDTO;

import com.example.dncompany.dto.user.mypage.HelpMeListDTO;
import com.example.dncompany.dto.user.mypage.PetSlideDTO;
import com.example.dncompany.dto.user.mypage.UserProfileDTO;
import com.example.dncompany.mapper.user.MypageMapper;
import com.example.dncompany.service.user.MypageService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import java.util.List;
import java.util.Optional;

@Slf4j
@Controller
@RequestMapping("/mypage")
@RequiredArgsConstructor
public class MypageController {
    public final MypageService mypageService;

    @GetMapping("/main")
    public String mypageMain(@SessionAttribute(value = "usersId", required = false) Long usersId,
                             Model model) {
        usersId = 6L;

        if(usersId == null) {
            return "redirect:/user/login";
        }

        List<PetSlideDTO> petList = mypageService.getPetSlide(usersId);
        model.addAttribute("petList", petList);
        UserProfileDTO userProfile = mypageService.userProfile(usersId);
        model.addAttribute("userProfile", userProfile);

        return "user/mypage/main";
    }


    @GetMapping("/add/pet")
    public String mypageAddPet() {
        return "user/mypage/add-pet";
    }

    //반려동물 정보 등록 처리
    @PostMapping("/add/pet")
    public String mypageAddPet(AddPetDTO addPetDTO, HttpSession session) {

        Long usersId = (Long) session.getAttribute("usersId");
        addPetDTO.setUsersId(usersId);


        log.debug("addPetDTO: {}", addPetDTO);

        mypageService.addPet(addPetDTO);

        return "redirect:/mypage/main";


    }

    ;

    @GetMapping("/update/pet")
    public String mypageUpdatePet() {


        return "user/mypage/update-pet";
    }


    @GetMapping("/update/profile")
    public String mypageUpdateProfile() {
        return "user/mypage/update-profile";
    }

    @GetMapping("/list/comment")
    public String mypageListComment() {
        return "user/mypage/work-list/comment-list";
    }

    @GetMapping("/list/content")
    public String mypageListContent() {
        return "user/mypage/work-list/content-list";
    }

    @GetMapping("/list/event")
    public String mypageListEvent() {
        return "user/mypage/work-list/event-list";
    }

    @GetMapping("/list/helpme")
    public String mypageListHelpme(@SessionAttribute(value = "usersId", required = false) Long usersId,
                                   Model model) {

        usersId=6L;


        List<HelpMeListDTO> helpMeListById = mypageService.helpMeListById(usersId);
        model.addAttribute("helpMeList", helpMeListById);
        return "user/mypage/work-list/helpme-list";
    }

    @GetMapping("/list/helpyou")
    public String mypageListHelpyou() {
        return "user/mypage/work-list/helpyou-list";
    }

    @GetMapping("/list/qna")
    public String mypageListQna() {
        return "user/mypage/work-list/qna-list";
    }
}
