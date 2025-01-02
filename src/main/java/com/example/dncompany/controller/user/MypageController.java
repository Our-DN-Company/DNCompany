package com.example.dncompany.controller.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/mypage")
@RequiredArgsConstructor
public class MypageController {

    @GetMapping("/main")
    public String mypageMain() {
        return "user/mypage/main";
    }

    @GetMapping("/add/pet")
        public String mypageAddPet() {
        return "user/mypage/add-pet";
    }



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
