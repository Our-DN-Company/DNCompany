package com.example.dncompany.mapper.user;


import com.example.dncompany.dto.user.mypage.*;

import org.apache.ibatis.annotations.Mapper;


import java.util.List;
import java.util.Optional;


@Mapper
public interface MypageMapper {

    void insertPet(AddPetDTO addPetDTO);

    //마이페이지 메인

    //정보출력
    List<PetListDTO> selectPetList(Long usersId);

    Optional<UserProfileDTO> userProfile(Long usersId);

    //사진 처리


   //게시글 요약

    List<HelpMeListDTO> MyPageMainHelpMeListById(Long usersId);









//   상세내역 자세히보기

    List<HelpMeListDTO> helpMeListById(Long usersId);

    List<HelpYouListDTO> helpYouListById(Long usersId);

    List<QnaListDTO> qnaListById(Long usersId);

    List<MypageZipBoardListDTO> MypageZipBoardListById(Long usersId);

    List<MypageZipAnswerListDTO> MypageZipAnswerListById(Long usersId);

    List<MypageReviewListDTO> MypageReviewListById(Long usersId);

    List<MypageDnBoardListDTO> MypageDnBoardListById(Long usersId);

    List<MypageDnSellListDTO> MypageDnSellListById(Long usersId);
}
