package com.example.dncompany.controller.user;

import com.example.dncompany.dto.user.mypage.AddPetDTO;

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

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/mypage")
@RequiredArgsConstructor
public class MypageController {
    public final MypageMapper mypageMapper;
    public final MypageService mypageService;

    @GetMapping("/main")
    public String mypageMain() {
        return "user/mypage/main";
    }



    @GetMapping("/add/pet")
        public String mypageAddPet() {
        return "user/mypage/add-pet";
    }

    //반려동물 정보 등록 처리
    @PostMapping("/add/pet")
    public String mypageAddPet(AddPetDTO addPetDTO, HttpSession session) {

        Long usersId = (Long)session.getAttribute("usersId");
        addPetDTO.setUsersId(usersId);




        log.debug("addPetDTO: {}", addPetDTO);

        mypageMapper.addPet(addPetDTO);

          return "redirect:/mypage/main";


    };

    @GetMapping("/update/pet")
    public String mypageUpdatePet() {return "user/mypage/update-pet";}



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
    public String mypageListHelpme() {
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
