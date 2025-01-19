package com.example.dncompany.service.user;


import com.example.dncompany.dto.page.PageDTO;
import com.example.dncompany.dto.page.PageRequestDTO;
import com.example.dncompany.dto.review.ReviewWriteDTO;
import com.example.dncompany.dto.user.mypage.*;
import com.example.dncompany.dto.user.mypage.PetImageDTO;
import com.example.dncompany.exception.user.UserNotFoundException;
import com.example.dncompany.mapper.user.MypageMapper;
import com.example.dncompany.mapper.user.MypagePetImageMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;


@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class MypageService {
    private final MypageMapper mypageMapper;
    private final MypagePetImageMapper mypagePetImageMapper;

    @Value("${pet.file.upload-path}")
    private String uploadPath;

    //반려동물 등록
    public void addPet(AddPetDTO addPetDTO,
                       Long usersId,
                       MultipartFile multipartFile) throws IOException {

        addPetDTO.setUsersId(usersId);
        mypageMapper.insertPet(addPetDTO);

        if (multipartFile == null || multipartFile.isEmpty()) {
            return;
        }

        String OriginalImageName = multipartFile.getOriginalFilename();
        String extension = OriginalImageName.substring(OriginalImageName.lastIndexOf("."));
        String uuid = UUID.randomUUID().toString();
        String datePath = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        String savePath = this.uploadPath + "/" + datePath;

        PetImageDTO petImageDTO = new PetImageDTO();
        petImageDTO.setPetUuid(uuid);
        petImageDTO.setPetOriginalImageName(OriginalImageName);
        petImageDTO.setPetExtension(extension);
        petImageDTO.setPetImagePath(datePath);
        petImageDTO.setPetId(addPetDTO.getPetId());

        log.debug("petImagDTO:{}", petImageDTO);


        File uploadDir = new File(savePath);

        if (!uploadDir.exists()) {

            uploadDir.mkdirs();
        }
        //실제 저장할 파일의 이름을 uuid로 사용
        String fileSystemName = uuid + extension;
//        fileSystemName : 서버의 저장할 파일 이름, uuid.확장자
        String fileFullPath = savePath + "/" + fileSystemName;
        //fileFullPath:파일 전체 경로(이름포함), C:/upload/free/yyyy/MM/dd/파일명.확장자
        File file = new File(fileFullPath);

        //실제 파일 저장하기(실제 저장처리는 이 한줄이 끝)
        multipartFile.transferTo(file);


        mypagePetImageMapper.insertPetImg(petImageDTO);
    }


//    마이페이지

    //정보 출력
    public List<PetListDTO> selectPetList(Long usersId) {
        return mypageMapper.selectPetList(usersId);
    }

    // 수정페이지 진입시 조회
    public PetDetailDTO getPetInfoByPetId(Long petId) {
        return mypageMapper.selectByPetId(petId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 펫ID"));
    }


    public UserProfileDTO userProfile(Long usersId) {
        return mypageMapper.userProfile(usersId)
                .orElseThrow(() -> new UserNotFoundException("존재하지 않는 회원 번호 입니다"));
    }

    //사진 입력


    //상세내역 요약
    public List<HelpMeListDTO> MyPageMainHelpMeListById(Long usersId) {
        log.debug("ServiceMyPageMainHelpMeListById:{}", usersId);

        return mypageMapper.MyPageMainHelpMeListById(usersId);

    }

    public List<HelpYouListDTO> MyPageMainHelpYouListById(Long usersId) {
        log.debug("ServiceMyPageMainHelpMeListById:{}", usersId);

        return mypageMapper.MyPageMainHelpYouListById(usersId);

    }


    //    정보 수정
    //    반려동물 수정
    public void modifyPetInfo(PetModifyDTO petModifyDTO, MultipartFile multipartFile) throws IOException {
        mypageMapper.updatePetInfo(petModifyDTO);

        //===============
        if (multipartFile == null || multipartFile.isEmpty()) {
            return;
        }

        mypagePetImageMapper.deleteByPetId(petModifyDTO.getPetId());

        String OriginalImageName = multipartFile.getOriginalFilename();
        String extension = OriginalImageName.substring(OriginalImageName.lastIndexOf("."));
        String uuid = UUID.randomUUID().toString();
        String datePath = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        String savePath = this.uploadPath + "/" + datePath;

        PetImageDTO petImageDTO = new PetImageDTO();
        petImageDTO.setPetUuid(uuid);
        petImageDTO.setPetOriginalImageName(OriginalImageName);
        petImageDTO.setPetExtension(extension);
        petImageDTO.setPetImagePath(datePath);
        petImageDTO.setPetId(petModifyDTO.getPetId());

        log.debug("petImagDTO:{}", petImageDTO);


        File uploadDir = new File(savePath);

        if (!uploadDir.exists()) {

            uploadDir.mkdirs();
        }
        //실제 저장할 파일의 이름을 uuid로 사용
        String fileSystemName = uuid + extension;
//        fileSystemName : 서버의 저장할 파일 이름, uuid.확장자
        String fileFullPath = savePath + "/" + fileSystemName;
        //fileFullPath:파일 전체 경로(이름포함), C:/upload/free/yyyy/MM/dd/파일명.확장자
        File file = new File(fileFullPath);

        //실제 파일 저장하기(실제 저장처리는 이 한줄이 끝)
        multipartFile.transferTo(file);


        mypagePetImageMapper.insertPetImg(petImageDTO);
    }


    //    반려동물 삭제
    public void removePetByPetId(Long petId) {
        mypageMapper.deletePetByPetId(petId);
    }

    //회원정보
    //회원정보 출력

    public UpdateUserProfileDTO selectUserProfileById(Long usersId) {
        return mypageMapper.selectUserProfileById(usersId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원번호입니다"));

    }
    //회원정보 수정
    public void updateUserProfile(UpdateUserProfileDTO updateUserProfile) {

        if (updateUserProfile.getAddressDetail() == null) {
            updateUserProfile.setAddressDetail(""); // 기본값 설정
        }
        if (updateUserProfile.getZipCode() == null) {
            updateUserProfile.setZipCode(""); // 기본값 설정
        }

        mypageMapper.updateUserProfile(updateUserProfile);

    }


    //페이징처리

    //Qna
    public PageDTO<QnaListDTO> qnaPageList(Long usersId, PageRequestDTO pageRequestDTO) {
        List<QnaListDTO> qnaList = mypageMapper.qnaListPage(usersId, pageRequestDTO);
        int total = mypageMapper.countQnaList(usersId);

        return new PageDTO<>(pageRequestDTO.getPage(),
                pageRequestDTO.getSize(),
                total,
                qnaList);


    }

    //helpMe
    public PageDTO<HelpMeListDTO> helpMeListPage(Long usersId, PageRequestDTO pageRequestDTO) {
        List<HelpMeListDTO> helpMeList = mypageMapper.helpMeListPage(usersId, pageRequestDTO);
        int total = mypageMapper.countHelpMeList(usersId);

        return new PageDTO<>(pageRequestDTO.getPage(),
                pageRequestDTO.getSize(),
                total,
                helpMeList);


    }

    //helpYou
    public PageDTO<HelpYouListDTO> helpYouListPage(Long usersId, PageRequestDTO pageRequestDTO, Long helpId) {
        List<HelpYouListDTO> helpYouList = mypageMapper.helpYouListPage(usersId, pageRequestDTO, helpId);
        int total = mypageMapper.countHelpYouList(usersId);

        return new PageDTO<>(pageRequestDTO.getPage(),
                pageRequestDTO.getSize(),
                total,
                helpYouList);


    }


    //zipBoard
    public PageDTO<MypageZipBoardListDTO> zipBoardListPage(Long usersId, PageRequestDTO pageRequestDTO) {
        List<MypageZipBoardListDTO> zipBoardList = mypageMapper.zipBoardListPage(usersId, pageRequestDTO);
        int total = mypageMapper.countZipBoardList(usersId);

        return new PageDTO<>(pageRequestDTO.getPage(),
                pageRequestDTO.getSize(),
                total,
                zipBoardList);


    }


    //zipBoard
    public PageDTO<MypageZipAnswerListDTO> zipAnswerListPage(Long usersId, PageRequestDTO pageRequestDTO) {
        List<MypageZipAnswerListDTO> zipAnswerList = mypageMapper.zipAnswerListPage(usersId, pageRequestDTO);
        int total = mypageMapper.countZipAnswerList(usersId);

        return new PageDTO<>(pageRequestDTO.getPage(),
                pageRequestDTO.getSize(),
                total,
                zipAnswerList);


    }

    //review
    public PageDTO<MypageReviewListDTO> reviewListPage(Long usersId, PageRequestDTO pageRequestDTO) {
        List<MypageReviewListDTO> reviewList = mypageMapper.reviewListPage(usersId, pageRequestDTO);
        int total = mypageMapper.countReviewList(usersId);

        return new PageDTO<>(pageRequestDTO.getPage(),
                pageRequestDTO.getSize(),
                total,
                reviewList);


    }


    //dnBuy
//    public PageDTO<MypageDnBuyListDTO> mypageDnBuyListPage(Long usersId, PageRequestDTO pageRequestDTO) {
//        List<MypageDnBuyListDTO> dnBoardList = mypageMapper.mypageDnBuyListPage(usersId, pageRequestDTO);
//        int total = mypageMapper.countMypageDnBuyList(usersId);
//
//        return new PageDTO<>(pageRequestDTO.getPage(),
//                pageRequestDTO.getSize(),
//                total,
//                dnBoardList);
//
//
//    }

    //dnSell
    public PageDTO<MypageDnSellListDTO> mypageDnSellListPage(Long usersId, PageRequestDTO pageRequestDTO) {
        List<MypageDnSellListDTO> dnSellList = mypageMapper.mypageDnSellListPage(usersId, pageRequestDTO);
        int total = mypageMapper.countMypageDnSellList(usersId);

        return new PageDTO<>(pageRequestDTO.getPage(),
                pageRequestDTO.getSize(),
                total,
                dnSellList);


    }

    public void modifyDnStatusByDnId(Long dnId){
        mypageMapper.updateDnStatusByDnId(dnId);
    }


    // dnLike
    public PageDTO<MypageDnLikeListDTO> getDnLikeListByUsersId(
            PageRequestDTO pageRequestDTO,
            Long usersId
    ) {
        List<MypageDnLikeListDTO> likeList = mypageMapper.selectDnLikeListByUsersId(pageRequestDTO, usersId);
        int total = mypageMapper.countDnLikeByUsersId(usersId);

        return  new PageDTO<>(pageRequestDTO.getPage(),
                pageRequestDTO.getSize(),
                total,
                likeList);
    }

    public boolean createReview(ReviewWriteDTO reviewWriteDTO) {
        try {
            return mypageMapper.insertReview(reviewWriteDTO) > 0;
        } catch (Exception e) {
            throw new RuntimeException("리뷰 등록 중 오류가 발생했습니다.", e);
        }
    }


    public void modifyHelpStatus(Long helpId, Long helpOfferId) {
        mypageMapper.updateHelpStatus(helpId, helpOfferId);
    }
}














