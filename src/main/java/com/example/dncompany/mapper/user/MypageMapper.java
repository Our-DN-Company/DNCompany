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

    // 수정페이지 진입시 조회
    Optional<PetDetailDTO> selectByPetId(Long petId);

    Optional<UserProfileDTO> userProfile(Long usersId);

    //사진 처리


    //게시글 요약

    List<HelpMeListDTO> MyPageMainHelpMeListById(Long usersId);

    List<HelpYouListDTO> MyPageMainHelpYouListById(Long usersId);


//   상세내역 자세히보기

    List<HelpMeListDTO> helpMeListById(Long usersId);

    List<HelpYouListDTO> helpYouListById(Long usersId);

    List<QnaListDTO> qnaListById(Long usersId);

    List<MypageZipBoardListDTO> MypageZipBoardListById(Long usersId);

    List<MypageZipAnswerListDTO> MypageZipAnswerListById(Long usersId);

    List<MypageReviewListDTO> MypageReviewListById(Long usersId);

    List<MypageDnBoardListDTO> MypageDnBoardListById(Long usersId);

    List<MypageDnSellListDTO> MypageDnSellListById(Long usersId);


    //    정보 수정
//    반려동물 수정
    void updatePetInfo(PetModifyDTO petModifyDTO);

    //    회원정보
//    회원정보 불러오기
    Optional<UpdateUserProfileDTO> selectUserProfileById(Long usersId);

    //    회원정보 수정
    void updateUserProfile(UpdateUserProfileDTO updateUserProfileDTO);

    //    반려동물 삭제
    void deletePetByPetId(Long petId);
}


















