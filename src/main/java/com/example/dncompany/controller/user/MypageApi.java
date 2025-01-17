package com.example.dncompany.controller.user;

import com.example.dncompany.dto.page.PageDTO;
import com.example.dncompany.dto.page.PageRequestDTO;
import com.example.dncompany.dto.user.mypage.MypageDnBuyListDTO;
import com.example.dncompany.dto.user.mypage.MypageDnSellListDTO;
import com.example.dncompany.dto.user.mypage.MypageZipBoardListDTO;
import com.example.dncompany.service.user.MypageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

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

//    @GetMapping("/v1/mypage/dn/buy")
//    public PageDTO<MypageDnBuyListDTO> getMypageDnBuy(PageRequestDTO pageRequestDTO,
//                                                        @SessionAttribute("usersId") Long usersId){
//        PageDTO<MypageDnBuyListDTO> pageDTO = mypageService.mypageDnBuyListPage(usersId, pageRequestDTO);
//        return pageDTO;
//    }

    @GetMapping("/v1/mypage/dn/sell")
    public PageDTO<MypageDnSellListDTO> getMypageDnSell(PageRequestDTO pageRequestDTO,
                                                          @SessionAttribute("usersId") Long usersId){
        PageDTO<MypageDnSellListDTO> pageDTO = mypageService.mypageDnSellListPage(usersId, pageRequestDTO);
        return pageDTO;
    }

    @PatchMapping("/v1/mypage/dn/sell/{dnId}")
    public void patchMypageDnSell(@PathVariable Long dnId) {
        mypageService.modifyDnStatusByDnId(dnId);
    }

}
