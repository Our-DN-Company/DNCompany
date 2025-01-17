package com.example.dncompany.controller.user;

import com.example.dncompany.dto.page.PageDTO;
import com.example.dncompany.dto.page.PageRequestDTO;
import com.example.dncompany.dto.user.mypage.*;

import com.example.dncompany.service.user.MypageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/mypage")
@RequiredArgsConstructor
public class MypageController {
    public final MypageService mypageService;

    @GetMapping("/main")
    public String mypageMain(@SessionAttribute(value = "usersId", required = false) Long usersId,
                             Model model) {
//        usersId = 6L;

        if(usersId == null) {
            return "redirect:/user/login";
        }
        //정보출력
        List<PetListDTO> petList = mypageService.selectPetList(usersId);
        log.info("petList: {}", petList);
        model.addAttribute("petList", petList);
        UserProfileDTO userProfile = mypageService.userProfile(usersId);
        model.addAttribute("userProfile", userProfile);

        //상세내역 요약

        List<HelpMeListDTO> MypageMainHelpMeList = mypageService.MyPageMainHelpMeListById(usersId);
        model.addAttribute("mainHelpMeList", MypageMainHelpMeList);
        log.info("MypageMainHelpMeList: {}", MypageMainHelpMeList);

        List<HelpYouListDTO> MypageMainHelpYouList = mypageService.MyPageMainHelpYouListById(usersId);
        model.addAttribute("mainHelpYouList", MypageMainHelpYouList);
        log.info("MypageMainHelpYouList: {}", MypageMainHelpYouList);

        return "user/mypage/main";
    }


    @GetMapping("/add/pet")
    public String mypageAddPet() {
        return "user/mypage/add-pet";
    }

    //반려동물 정보 등록 처리
    @PostMapping("/add/pet")
    public String mypageAddPet(AddPetDTO addPetDTO,
                               @SessionAttribute(value = "usersId",required = false) Long usersId,
                               @RequestParam(value = "petImg",required = false) MultipartFile multipartFile) {


//            usersId = 6L;
//        log.debug("addPetDTO: {}", addPetDTO);
//        log.debug("imgFile={}",multipartFile.isEmpty());

        try {
            mypageService.addPet(addPetDTO,usersId,multipartFile);
        } catch (IOException e) {
            log.error(e.getMessage());
        }



        return "redirect:/mypage/main";


    }

    ;

    @GetMapping("/update/pet")
    public String mypageUpdatePet(@SessionAttribute(value = "usersId", required = false) Long usersId,
                                  Long petId,
                                  Model modal) {
        PetDetailDTO petDetailDTO = mypageService.getPetInfoByPetId(petId);
        modal.addAttribute("petDetailDTO", petDetailDTO);

        return "user/mypage/update-pet";
    }

    @PostMapping("/update/pet")
    public String mypageUpdatePet(PetModifyDTO petModifyDTO, @RequestParam("petImg") MultipartFile multipartFile) {
        log.info("petModifyDTO: {}", petModifyDTO);
        try {
            mypageService.modifyPetInfo(petModifyDTO, multipartFile);
        } catch (IOException e) {
            log.error(e.getMessage());
        }

        return "redirect:/mypage/main";
    }


    @GetMapping("/update/profile")
    public String mypageUpdateProfile(@SessionAttribute(value = "usersId", required = false) Long usersId,
                                      Model model) {
        UpdateUserProfileDTO selectUserProfileById = mypageService.selectUserProfileById(usersId);
        model.addAttribute("userProfile", selectUserProfileById);
        return "user/mypage/update-profile";
    }

    @PostMapping("/update/profile")
    public String mypageUpdateProfile(UpdateUserProfileDTO updateUserProfileDTO){
        log.debug("updateUserProfileDTO: {}", updateUserProfileDTO);

        mypageService.updateUserProfile(updateUserProfileDTO);
        log.debug("updateUserProfileDTO: {}", updateUserProfileDTO);
        return "redirect:/mypage/main";
    }

    @GetMapping("/list/zip")
    public String mypageListZip(@SessionAttribute(value = "usersId", required = false) Long usersId,
                                PageRequestDTO pageRequestDTO,
                                Model model) {
        PageDTO<MypageZipBoardListDTO> zipBoardPageDTO= mypageService.zipBoardListPage (usersId, pageRequestDTO);
        model.addAttribute("zipBoardPageDTO", zipBoardPageDTO);

        PageDTO<MypageZipAnswerListDTO> zipAnswerPageDTO= mypageService.zipAnswerListPage (usersId, pageRequestDTO);
        model.addAttribute("zipAnswerPageDTO", zipAnswerPageDTO);
        return "user/mypage/work-list/mypage-zip-list";
    }

    @GetMapping("/list/dn")
    public String mypageListContent() {
        return "user/mypage/work-list/dn-list";
    }

    @GetMapping("/list/dn-like")
    public String mypageListDnLike(@SessionAttribute("usersId") Long usersId,
                                   PageRequestDTO pageRequestDTO,
                                   Model model) {
        PageDTO<MypageDnLikeListDTO> pageDTO = mypageService.getDnLikeListByUsersId(pageRequestDTO, usersId);
        model.addAttribute("pageDTO", pageDTO);

        return "user/mypage/work-list/dn-like";
    }

    @GetMapping("/list/review")
    public String mypageListEvent(@SessionAttribute(value = "usersId", required = false) Long usersId,
                                  PageRequestDTO pageRequestDTO,
                                  Model model) {
        PageDTO<MypageReviewListDTO> pageDTO= mypageService.reviewListPage(usersId, pageRequestDTO);
        model.addAttribute("pageDTO", pageDTO);


        return "user/mypage/work-list/review-list";
    }

    @GetMapping("/list/helpme")
    public String mypageListHelpme(@SessionAttribute(value = "usersId", required = false) Long usersId,
                                   PageRequestDTO pageRequestDTO,
                                   Model model) {

        PageDTO<HelpMeListDTO> pageDTO= mypageService.helpMeListPage (usersId, pageRequestDTO);
        model.addAttribute("pageDTO", pageDTO);
        return "user/mypage/work-list/helpme-list";
    }

    @GetMapping("/list/helpyou")
    public String mypageListHelpyou(@SessionAttribute(value = "usersId", required = false) Long usersId,
                                    PageRequestDTO pageRequestDTO,
                                    Model model) {
        PageDTO<HelpYouListDTO> pageDTO= mypageService.helpYouListPage (usersId, pageRequestDTO);
        model.addAttribute("pageDTO", pageDTO);

        return "user/mypage/work-list/helpyou-list";
    }

    @GetMapping("/list/qna")
    public String mypageListQna(@SessionAttribute(value = "usersId", required = false) Long usersId,
                                PageRequestDTO pageRequestDTO,
                                Model model) {


        PageDTO<QnaListDTO> pageDTO= mypageService.qnaPageList (usersId, pageRequestDTO);
        model.addAttribute("pageDTO", pageDTO);
        return "user/mypage/work-list/qna-list";
    }








    //    삭제 처리
    @GetMapping("/delete/pet")
    public String mypageDeletePet(Long petId) {
        mypageService.removePetByPetId(petId);
        return "redirect:/mypage/main";
    }
}