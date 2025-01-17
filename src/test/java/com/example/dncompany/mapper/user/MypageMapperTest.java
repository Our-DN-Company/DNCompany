package com.example.dncompany.mapper.user;

import com.example.dncompany.dto.user.mypage.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
class MypageMapperTest {

    @Autowired
    MypageMapper mypageMapper;
    AddPetDTO addPetDTO;
    UserProfileDTO userProfileDTO;
    ReviewDTO reviewDTO;
    @BeforeEach
    void setUp(){
        addPetDTO = new AddPetDTO();
//        addPetDTO.setPetId(6L);
        addPetDTO.setPetName("테스트");
        addPetDTO.setPetSpecies("강아지");
        addPetDTO.setPetGender("F");
        addPetDTO.setUsersId(6L);

        addPetDTO.setPetBirthDate(LocalDate.of(1990, 1, 1));
        addPetDTO.setAdoptionDate(LocalDate.of(1990, 1, 1));



    }

    @Test
    void insertPet(){
        mypageMapper.insertPet(addPetDTO);

        List<PetListDTO> petList = mypageMapper.selectPetList(6L);

        assertThat(petList)
                .isNotEmpty()
                .extracting("petName")
                .contains("테스트");
    }


    @Test
    void userProfile() {
        Optional<UserProfileDTO> userProfile = mypageMapper.userProfile(21L);


    }

    @Test
    void helpMeListById() {


    }

    @Test
    void mypageMainList(){

        List<HelpMeListDTO> helpMeList = mypageMapper.MyPageMainHelpMeListById(6L);

        assertThat(helpMeList)
                .isNotEmpty();
    }


    @Test
    void updateUserProfileInfo() {
        Optional<UpdateUserProfileDTO> updateUserProfile = mypageMapper.selectUserProfileById(81L);

    }


//    @Test
//    void reviewInfo() {
//        reviewDTO = new ReviewDTO();
//        reviewDTO.setReviewContent("Test");
//        reviewDTO.setReviewStarRating(5);
//        reviewDTO.setHelpId(142L);
//        reviewDTO.setUsersId(24L);
//    }
}