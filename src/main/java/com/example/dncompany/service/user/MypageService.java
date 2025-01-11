package com.example.dncompany.service.user;


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
import java.util.Optional;
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
    public PetDetailDTO getPetInfoByPetId(Long petId){
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





//    상세내역 페이지

    public List<HelpMeListDTO> helpMeListById(Long usersId) {
        return mypageMapper.helpMeListById(usersId);
    }

    public List<HelpYouListDTO> helpYouListById(Long usersId) {
        return mypageMapper.helpYouListById(usersId);
    }

    public List<QnaListDTO> qnaListById(Long usersId) {
        return mypageMapper.qnaListById(usersId);
    }

    public List<MypageZipAnswerListDTO> MypageZipAnswerListById(Long usersId) {
        return mypageMapper.MypageZipAnswerListById(usersId);
    }

    public List<MypageZipBoardListDTO> MypageZipBoardListById(Long usersId) {
        return mypageMapper.MypageZipBoardListById(usersId);
    }

    public List<MypageReviewListDTO> MypageReviewListById(Long usersId) {
        return mypageMapper.MypageReviewListById(usersId);
    }

    public List<MypageDnBoardListDTO> MypageDnBoardListById(Long usersId) {
        return mypageMapper.MypageDnBoardListById(usersId);
    }

    public List<MypageDnSellListDTO> MypageDnSellListById(Long usersId) {
        return mypageMapper.MypageDnSellListById(usersId);
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
    public void removePetByPetId(Long petId){
        mypageMapper.deletePetByPetId(petId);
    }
}














