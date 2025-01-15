package com.example.dncompany.controller.user;

import com.example.dncompany.dto.page.PageDTO;
import com.example.dncompany.dto.page.PageRequestDTO;
import com.example.dncompany.dto.user.mypage.MypageDnBoardListDTO;
import com.example.dncompany.dto.user.mypage.MypageDnSellListDTO;
import com.example.dncompany.dto.user.mypage.MypageZipAnswerListDTO;
import com.example.dncompany.dto.user.mypage.MypageZipBoardListDTO;
import com.example.dncompany.service.user.MypageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class MypageApi {
    private final MypageService mypageService;

    @GetMapping("/v1/mypage/zips")
    public PageDTO<MypageZipBoardListDTO> getMypageZips(PageRequestDTO pageRequestDTO,
                                @SessionAttribute("usersId") Long usersId) {
        PageDTO<MypageZipBoardListDTO> mypageZipBoardListDTOPageDTO = mypageService.zipBoardListPage(usersId, pageRequestDTO);
        return mypageZipBoardListDTOPageDTO;


    }

    @GetMapping("/v1/mypage/dn/board")
    public PageDTO<MypageDnBoardListDTO> getMypageDnBoard(PageRequestDTO pageRequestDTO,
                                                          @SessionAttribute("usersId") Long usersId){
        PageDTO<MypageDnBoardListDTO> mypageDnBoardListDTOPageDTO = mypageService.mypageDnBoardListPage(usersId, pageRequestDTO);
        return mypageDnBoardListDTOPageDTO;
    }

    @GetMapping("/v1/mypage/dn/sell")
    public PageDTO<MypageDnSellListDTO> getMypageDnSell(PageRequestDTO pageRequestDTO,
                                                          @SessionAttribute("usersId") Long usersId){
        PageDTO<MypageDnSellListDTO> mypageDnSellListDTOPageDTO = mypageService.mypageDnSellListPage(usersId, pageRequestDTO);
        return mypageDnSellListDTOPageDTO;
    }

}
